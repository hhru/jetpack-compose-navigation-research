package ru.hh.navigation.appyx.presentation

import android.os.Parcelable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.navigation.Resolver
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.pop
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackSlider
import kotlinx.parcelize.Parcelize
import ru.hh.navigation.common.randomString

internal class StackNode(
    buildContext: BuildContext,
    initialNavTarget: NavTarget = NavTarget.SampleScreen(randomString()),
    private val backStack: BackStack<NavTarget> = BackStack(
        initialElement = initialNavTarget,
        savedStateMap = buildContext.savedStateMap,
    ),
) : ParentNode<NavTarget>(
    navModel = backStack,
    buildContext = buildContext
), Resolver<NavTarget> by NavigationResolver(
    backStack,
    { backStack.pop() }
) {

    @Composable
    override fun View(modifier: Modifier) {
        Children(
            navModel = backStack,
            transitionHandler = rememberBackstackSlider(
                clipToBounds = true,
                transitionSpec = { spring(stiffness = Spring.StiffnessMediumLow) }
            ),
        )
    }
}

/**
 * You can create this class inside the body of RootNode
 *
 * Note: You must apply the 'kotlin-parcelize' plugin to use @Parcelize
 * https://developer.android.com/kotlin/parcelize
 */
internal sealed interface NavTarget : Parcelable {

    @Parcelize
    data class SampleScreen(
        val tittle: String,
    ) : NavTarget

    @Parcelize
    data class ComplexNavigation(
        val title: String,
    ) : NavTarget

    @Parcelize
    data class Stack(
        val initialNavTarget: NavTarget = SampleScreen(randomString()),
    ) : NavTarget

    @Parcelize
    object MultiStack : NavTarget

    @Parcelize
    data class Flow(val firstScreenTitle: String) : NavTarget

}

internal class NavigationResolver(
    private val stack: BackStack<NavTarget>,
    private val onBack: () -> Unit,
) : Resolver<NavTarget> {

    override fun resolve(navTarget: NavTarget, buildContext: BuildContext): Node =
        when (navTarget) {
            is NavTarget.SampleScreen ->
                SampleScreen(buildContext, navTarget.tittle, stack)
            is NavTarget.ComplexNavigation ->
                ComplexNavigationScreen(buildContext, navTarget.title, stack)
            is NavTarget.Stack ->
                StackNode(buildContext, initialNavTarget = navTarget.initialNavTarget)
            is NavTarget.MultiStack ->
                MultiStackScreen(buildContext, 1)
            is NavTarget.Flow ->
                FlowNavigationScreen(buildContext, navTarget.firstScreenTitle, onBack)
        }

}
