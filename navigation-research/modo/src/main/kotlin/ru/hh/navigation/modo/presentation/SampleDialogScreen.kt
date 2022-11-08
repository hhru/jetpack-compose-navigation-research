package ru.hh.navigation.modo.presentation

import androidx.compose.runtime.Composable
import com.github.terrakok.modo.DialogScreen
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.*
import kotlinx.parcelize.Parcelize
import ru.hh.navigation.common.DialogContent
import ru.hh.navigation.common.randomString

@Parcelize
internal class SampleDialogScreen(
    private val title: String,
    override val screenKey: ScreenKey = generateScreenKey()
) : DialogScreen {

    @Composable
    override fun Content() {
        val parent = LocalContainerScreen.current
        DialogContent(
            openScreen = { closeDialog ->
                if (closeDialog) {
                    parent.dispatch(
                        CompositeAction(
                            Back,
                            Forward(SampleScreen(randomString()))
                        )
                    )
                } else {
                    parent.forward(SampleScreen(randomString()))
                }
            },
            openDialog = { parent.forward(SampleDialogScreen(randomString())) },
            closeDialog = { parent.back() },
            screenTitle = title,
        )
    }

}