package ru.hh.navigation.voyager.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.hh.navigation.common.ComplexNavigationSampleContent
import ru.hh.navigation.common.randomString

internal class ComplexNavigationScreen(
    private val text: String = randomString(),
) : AndroidScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val showNotSupported = LocalShowToast.current
        ComplexNavigationSampleContent(
            openStack = {
                navigator.push(
                    listOf(
                        ComplexNavigationScreen(),
                        ComplexNavigationScreen(),
                        ComplexNavigationScreen()
                    )
                )
            },
            closeStack = { navigator.popUntilRoot() },
            closePrevScreen = { showNotSupported() },
            replaceScreen = { navigator.replace(ComplexNavigationScreen()) },
            screenTitle = text,
            modifier = Modifier.fillMaxSize()
        )
    }

}
