package ru.hh.navigation.appyx.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.spotlight.Spotlight
import com.bumble.appyx.navmodel.spotlight.activeIndex
import com.bumble.appyx.navmodel.spotlight.operation.activate
import ru.hh.navigation.common.MultiStackScreenContent
import ru.hh.navigation.common.TABS

internal class MultiStackScreen(
    buildContext: BuildContext,
    private val firstSelectedTab: Int,
    private val spotlight: Spotlight<NavTarget.Stack> = Spotlight(
        TABS.map { NavTarget.Stack() },
        initialActiveIndex = firstSelectedTab,
        savedStateMap = buildContext.savedStateMap
    ),
) : ParentNode<NavTarget.Stack>(
    navModel = spotlight,
    buildContext = buildContext,
) {

    @Composable
    override fun View(modifier: Modifier) {
        MultiStackScreenContent(
            selectedPos = spotlight.activeIndex().collectAsState(initial = 0).value,
            onTabClick = { spotlight.activate(it) }
        ) {
            Box(Modifier.padding(it)) {
                Children(navModel)
            }
        }
    }

    override fun resolve(navTarget: NavTarget.Stack, buildContext: BuildContext): Node =
        StackNode(buildContext, navTarget.initialNavTarget)

}
