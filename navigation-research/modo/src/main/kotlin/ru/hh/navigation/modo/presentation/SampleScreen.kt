package ru.hh.navigation.modo.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.forward
import kotlinx.parcelize.Parcelize
import ru.hh.navigation.common.SampleScreenContent
import ru.hh.navigation.common.randomString

@Parcelize
internal class SampleScreen(
    private val title: String,
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {
        val parent = LocalContainerScreen.current
        val showNotSupported = LocalShowNotSupported.current
        SampleScreenContent(
            openScreen = { parent.forward(SampleScreen(randomString())) },
            openDialog = { parent.forward(SampleDialogScreen(randomString())) },
            openBottomSheet = { showNotSupported() },
            // этот кейс просто копия кейса ниже, поэтому не будем его рисовать
            startFlowWithoutHeader = null,
            startFlowWithHeader = { parent.forward(FlowNavigationScreen(randomString())) },
            openMultiscreen = { parent.forward(MultiStackScreen(1)) },
            openScreenModel = { },
            openComplexNavigation = { parent.forward(ComplexNavigationScreen(randomString())) },
            screenTitle = title,
            modifier = Modifier.fillMaxSize()
        )
    }

}