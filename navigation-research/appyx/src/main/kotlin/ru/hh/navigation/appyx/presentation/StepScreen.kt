package ru.hh.navigation.appyx.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import ru.hh.navigation.common.StepContent

class StepScreen(
    buildContext: BuildContext,
    private val title: String,
    private val isOnNextEnabled: Boolean,
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
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
