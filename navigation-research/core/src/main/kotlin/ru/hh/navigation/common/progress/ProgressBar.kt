@file:Suppress("detekt.LongMethod", "detekt.ComplexMethod")

package ru.hh.navigation.common.progress

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import ru.hh.navigation.common.toPx
import ru.hh.navigation.common.progress.styles.ProgressBarColors
import ru.hh.navigation.common.progress.styles.ProgressBarSizes


private const val DEFAULT_STEPS_COUNT = 6
private const val COLOR_COUNT = 3
private const val MIN_STEP_COUNT = 3

private const val MAX_PROGRESS = 100
private const val ANIMATION_DURATION = 500


/**
 * @param step Текущий шаг
 * @param stepsCount Общее количество шагов
 * @param type Тип. Шаги [ProgressBarType.STEP] или проценты [ProgressBarType.PERCENT]
 * @param colors Цвета. Для стандартного прогрессбара (красный, оранжевый, зеленый), захардкожены внутри функции
 * @param sizes Размеры
 * @param withAnimation Анимация прогресса и смены цветов
 * @param onAnimationFinish Коллбэк окончания анимации
 */
@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    step: Int = 0,
    stepsCount: Int = DEFAULT_STEPS_COUNT,
    type: ProgressBarType = ProgressBarType.STEP,
    colors: ProgressBarColors = ProgressBarColors.Default,
    sizes: ProgressBarSizes = ProgressBarSizes.Default,
    withAnimation: Boolean = false,
    onAnimationFinish: (() -> Unit)? = null,
) {
    val normalizedStepsCount = if (stepsCount < MIN_STEP_COUNT) MIN_STEP_COUNT else stepsCount

    val maxValue = when (type) {
        ProgressBarType.STEP -> normalizedStepsCount
        ProgressBarType.PERCENT -> step / (MAX_PROGRESS / normalizedStepsCount) // % -> уменьшать в меньшую сторону
    }

    val normalizedStep = step.coerceAtMost(maxValue).coerceAtLeast(0)
    val colorStep = normalizedStepsCount / COLOR_COUNT
    val progress = normalizedStep / normalizedStepsCount.toFloat()
    val internalProgressColor = colors.progressColor ?: run {
        when {
            normalizedStep <= colorStep -> MaterialTheme.colors.error
            normalizedStep in colorStep + 1..colorStep * 2 -> MaterialTheme.colors.secondary
            else -> MaterialTheme.colors.primary
        }
    }

    val animatedProgress = if (withAnimation) {
        animateFloatAsState(
            targetValue = progress,
            animationSpec = tween(durationMillis = ANIMATION_DURATION),
            finishedListener = { onAnimationFinish?.invoke() },
        ).value
    } else {
        progress
    }

    val animatedProgressColor = if (withAnimation) {
        animateColorAsState(
            targetValue = internalProgressColor,
            animationSpec = tween(durationMillis = ANIMATION_DURATION),
        ).value
    } else {
        internalProgressColor
    }

    val delimiterWidthPx = sizes.delimiterWidth.toPx()

    Canvas(
        modifier = Modifier
            .height(sizes.progressBarHeight)
            .fillMaxWidth()
            .clip(RoundedCornerShape(sizes.progressBarHeight / 2)) // закругления на концах прогрессбара
            .then(modifier),
    ) {
        val heightF = size.height
        val widthF = size.width
        val progressWidth = widthF * animatedProgress

        val delimitersPath = Path().apply {
            (1 until normalizedStepsCount).forEach { step ->
                val delimiterLeft = widthF * (step.toFloat() / normalizedStepsCount) - delimiterWidthPx / 2

                addRect(
                    Rect(Offset(x = delimiterLeft, y = 0f), Size(width = delimiterWidthPx, height = heightF)),
                )
            }
        }

        if (colors.delimiterColor == null) {
            // вырезаем разделители из прогресса
            clipPath(path = delimitersPath, clipOp = ClipOp.Difference) {
                drawProgressBar(
                    progressWidth = progressWidth,
                    emptyProgressWidth = widthF - progressWidth,
                    heightF = widthF,
                    animatedProgressColor = animatedProgressColor,
                    emptyProgressColor = colors.emptyProgressColor,
                )
            }
        } else {
            drawProgressBar(
                progressWidth = progressWidth,
                emptyProgressWidth = widthF - progressWidth,
                heightF = widthF,
                animatedProgressColor = animatedProgressColor,
                emptyProgressColor = colors.emptyProgressColor,
            )

            // рисуем разделители на прогрессе
            drawPath(path = delimitersPath, color = colors.delimiterColor)
        }
    }
}

private fun DrawScope.drawProgressBar(
    progressWidth: Float,
    emptyProgressWidth: Float,
    heightF: Float,
    animatedProgressColor: Color,
    emptyProgressColor: Color,
) {
    // рисуем цветной прогресс
    drawRect(
        color = animatedProgressColor,
        topLeft = Offset(x = 0f, y = 0f),
        size = Size(width = progressWidth, height = heightF),
    )

    // рисуем серенький остаток
    drawRect(
        color = emptyProgressColor,
        topLeft = Offset(x = progressWidth, y = 0f),
        size = Size(width = emptyProgressWidth, height = heightF),
    )
}
