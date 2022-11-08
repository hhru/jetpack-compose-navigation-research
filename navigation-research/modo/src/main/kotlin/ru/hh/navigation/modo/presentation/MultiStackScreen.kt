package ru.hh.navigation.modo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.terrakok.modo.multiscreen.MultiScreen
import com.github.terrakok.modo.multiscreen.MultiScreenNavModel
import com.github.terrakok.modo.multiscreen.selectContainer
import kotlinx.parcelize.Parcelize
import ru.hh.navigation.common.MultiStackScreenContent
import ru.hh.navigation.common.TABS

@Parcelize
internal class MultiStackScreen(
    private val firstSelectedTab: Int,
    private val navState: MultiScreenNavModel = MultiScreenNavModel(
        TABS.map { ModoStackScreen() },
        firstSelectedTab,
    ),
) : MultiScreen(navState) {

    @Composable
    override fun Content() {
        MultiStackScreenContent(
            selectedPos = navigationState.selected,
            onTabClick = { selectContainer(it) }
        ) {
            Box(Modifier.padding(it)) {
                SelectedScreen()
            }
        }
    }

}
