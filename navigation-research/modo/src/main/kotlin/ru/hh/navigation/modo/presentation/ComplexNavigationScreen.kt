package ru.hh.navigation.modo.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.*
import kotlinx.parcelize.Parcelize
import ru.hh.navigation.common.ComplexNavigationSampleContent
import ru.hh.navigation.common.randomString
import kotlin.random.Random

@Parcelize
internal class ComplexNavigationScreen(
    private val text: String = randomString(),
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content() {
        val showNotSupported = LocalShowNotSupported.current
        val parent = LocalContainerScreen.current as StackScreen
        val closePrevEnabled by remember {
            derivedStateOf {
                (parent.navigationState as StackState).stack.size > 1
            }
        }
        val closePrevScreen = remember(closePrevEnabled) {
            if (closePrevEnabled) {
                {
                    val children = parent.navigationState.getChildScreens()
                    parent.removeScreen(children.lastIndex - 1)
                }
            } else {
                null
            }
        }
        ComplexNavigationSampleContent(
            openStack = {
                Random.nextInt()
                parent.forward(
                    ComplexNavigationScreen(randomString()),
                    ComplexNavigationScreen(randomString()),
                    ComplexNavigationScreen(randomString()),
                )
            },
            closeStack = { parent.backToRoot() },
            closePrevScreen = closePrevScreen,
            replaceScreen = { parent.replace(ComplexNavigationScreen(randomString())) },
            screenTitle = text,
            modifier = Modifier.fillMaxSize()
        )
    }

}
