package ru.hh.navigation.modo.presentation

import androidx.compose.runtime.Composable
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.parcelize.Parcelize
import ru.hh.navigation.common.randomString

@Parcelize
internal class ModoStackScreen(
    private val backStack: StackNavModel = StackNavModel(SampleScreen(randomString())),
) : StackScreen(backStack) {

    @Composable
    override fun Content() {
        TopScreenContent { SlideTransition() }
    }
}