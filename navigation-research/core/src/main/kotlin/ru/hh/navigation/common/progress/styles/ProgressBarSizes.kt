package ru.hh.navigation.common.progress.styles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Описание размеров прогрессбара
 *
 * @param progressBarHeight Высота прогрессбара
 * @param delimiterWidth Ширина разделителя между шагами
 */
@Stable
data class ProgressBarSizes(
    val progressBarHeight: Dp,
    val delimiterWidth: Dp,
) {

    companion object {

        @Stable
        val Default: ProgressBarSizes
            @Composable
            get() = ProgressBarSizes(
                progressBarHeight = 8.dp,
                delimiterWidth = 2.dp,
            )

    }

}
