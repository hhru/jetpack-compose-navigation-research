package ru.hh.navigation.appyx.presentation

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.composable.childrenAsState
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.push
import kotlinx.parcelize.Parcelize
import ru.hh.navigation.common.FlowScreenContainer
import ru.hh.navigation.common.STEPS_COUNT
import ru.hh.navigation.common.randomString
import timber.log.Timber

internal val LocalOpenNextStep = compositionLocalOf<() -> Unit> { error("not provided") }
internal val LocalFinishFlow = compositionLocalOf<() -> Unit> { error("not provided") }

internal class FlowNavigationScreen(
    buildContext: BuildContext,
    firstScreenTitle: String,
    private val onBack: () -> Unit,
    private val backStack: BackStack<Step> = BackStack(
        Step(firstScreenTitle, isOnNextEnabled = true),
        buildContext.savedStateMap
    ),
) : ParentNode<FlowNavigationScreen.Step>(
    buildContext = buildContext,
    navModel = backStack
) {

    @Composable
    override fun View(modifier: Modifier) {
        val children by backStack.childrenAsState()
        val step by remember { derivedStateOf { children.size } }
        SideEffect {
            Timber.d("step = $step")
        }
        FlowScreenContainer(step = step, stepsCount = STEPS_COUNT) {
            CompositionLocalProvider(
                LocalOpenNextStep provides {
                    backStack.push(Step(randomString(), isOnNextEnabled = step + 1 < STEPS_COUNT))
                },
                LocalFinishFlow provides onBack
            ) {
                Children(backStack)
            }
        }
    }

    override fun resolve(navTarget: Step, buildContext: BuildContext): Node = StepScreen(
        buildContext,
        navTarget.title,
        navTarget.isOnNextEnabled
    )

    @Parcelize
    data class Step(val title: String, val isOnNextEnabled: Boolean) : Parcelable

}
