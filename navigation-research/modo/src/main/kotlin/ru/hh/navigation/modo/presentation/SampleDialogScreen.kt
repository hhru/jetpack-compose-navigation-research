package ru.hh.navigation.modo.presentation

import androidx.compose.runtime.Composable
import com.github.terrakok.modo.*
import com.github.terrakok.modo.stack.*
import kotlinx.parcelize.Parcelize
import ru.hh.navigation.common.DialogContent
import ru.hh.navigation.common.randomString

@OptIn(ExperimentalModoApi::class)
@Parcelize
internal class SampleDialogScreen(
    private val title: String,
    override val screenKey: ScreenKey = generateScreenKey()
) : DialogScreen {

    @Composable
    override fun Content() {
        val parent = LocalContainerScreen.current as StackScreen
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