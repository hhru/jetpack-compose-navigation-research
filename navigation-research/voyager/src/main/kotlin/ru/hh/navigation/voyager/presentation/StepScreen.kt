package ru.hh.navigation.voyager.presentation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import ru.hh.navigation.common.StepContent

class StepScreen(
    private val title: String,
    private val isOnNextEnabled: Boolean,
) : Screen {

    override val key: ScreenKey = uniqueScreenKey

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
