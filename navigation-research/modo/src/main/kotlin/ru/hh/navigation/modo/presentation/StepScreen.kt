package ru.hh.navigation.modo.presentation

import androidx.compose.runtime.Composable
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import ru.hh.navigation.common.StepContent

@Parcelize
class StepScreen(
    private val title: String,
    private val isOnNextEnabled: Boolean,
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content() {
        val onNext = LocalOpenNextStep.current
        val onFinish = LocalFinishFlow.current
        StepContent(
            text = title,
            isOnNextEnabled = isOnNextEnabled,
            onNextClick = onNext,
            onFinishFlowClick = onFinish
        )
    }

}
