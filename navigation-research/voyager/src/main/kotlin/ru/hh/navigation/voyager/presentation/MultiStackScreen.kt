package ru.hh.navigation.voyager.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import ru.hh.navigation.common.MultiStackScreenContent
import ru.hh.navigation.common.TABS
import ru.hh.navigation.common.randomString

internal class MultiStackScreen(
    private val firstSelectedTab: Int,
) : AndroidScreen() {

    private val tabs = TABS.map {
        StackTab()
    }

    @Composable
    override fun Content() {
        TabNavigator(tabs[firstSelectedTab]) { tabNavigator ->
            val index = remember { derivedStateOf { tabs.indexOf(tabNavigator.current) } }
            MultiStackScreenContent(
                selectedPos = index.value,
                onTabClick = {
                    tabNavigator.current = tabs[it]
                }
            ) {
                Box(Modifier.padding(it)) {
                    CurrentTab()
                }
            }
        }
    }

}

internal class StackTab : Tab {

    override val key: ScreenKey = uniqueScreenKey

    override val options: TabOptions
        @Composable
        get() {
            // просто заглушка, т.к. по факту мы это не используем
            return remember {
                TabOptions(
                    index = 0u,
                    title = "",
                    icon = null
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(screen = SampleScreen(randomString()))
    }

}
