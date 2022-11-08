package ru.hh.navigation.modo.presentation

import androidx.compose.runtime.*
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.back
import com.github.terrakok.modo.stack.forward
import kotlinx.parcelize.Parcelize
import ru.hh.navigation.common.FlowScreenContainer
import ru.hh.navigation.common.STEPS_COUNT
import ru.hh.navigation.common.randomString

internal val LocalOpenNextStep = compositionLocalOf<() -> Unit> { error("not provided") }
internal val LocalFinishFlow = compositionLocalOf<() -> Unit> { error("not provided") }

@Parcelize
internal class FlowNavigationScreen(
    private val backStack: StackNavModel,
) : StackScreen(backStack) {

    constructor(firstScreenTitle: String) : this(
        StackNavModel(StepScreen(firstScreenTitle, isOnNextEnabled = true))
    )

    @Composable
    override fun Content() {
        val step by remember { derivedStateOf { navigationState.stack.size } }
        val parent = LocalContainerScreen.current
        FlowScreenContainer(step = step, stepsCount = STEPS_COUNT) {
            CompositionLocalProvider(
                LocalOpenNextStep provides {
                    backStack.forward(StepScreen(randomString(), isOnNextEnabled = step + 1 < STEPS_COUNT))
                },
                LocalFinishFlow provides { parent.back() }
            ) {
                TopScreenContent()
            }
        }
    }

}
