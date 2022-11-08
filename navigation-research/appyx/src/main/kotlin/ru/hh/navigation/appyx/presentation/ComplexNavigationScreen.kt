package ru.hh.navigation.appyx.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.childrenAsState
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx.navmodel.backstack.operation.remove
import com.bumble.appyx.navmodel.backstack.operation.replace
import ru.hh.navigation.common.ComplexNavigationSampleContent
import ru.hh.navigation.common.randomString
import kotlin.random.Random

internal class ComplexNavigationScreen(
    buildContext: BuildContext,
    private val text: String = randomString(),
    private val stack: BackStack<NavTarget>,
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val showNotSupported = LocalShowNotSupported.current
        val elements by stack.childrenAsState()
        val closePrevEnabled by remember {
            derivedStateOf {
                elements.size > 1
            }
        }
        val closePrevScreen = remember(closePrevEnabled) {
            if (closePrevEnabled) {
                {
                    val element = elements[elements.lastIndex - 1]
                    stack.remove(element.key)
                }
            } else {
                null
            }
        }
        ComplexNavigationSampleContent(
            openStack = {
                Random.nextInt()
                stack.push(NavTarget.ComplexNavigation(randomString()))
                stack.push(NavTarget.ComplexNavigation(randomString()))
                stack.push(NavTarget.ComplexNavigation(randomString()))
            },
            closeStack = {
                showNotSupported()
            },
            closePrevScreen = closePrevScreen,
            replaceScreen = { stack.replace(NavTarget.ComplexNavigation(randomString())) },
            screenTitle = text,
            modifier = Modifier.fillMaxSize()
        )
    }

}
