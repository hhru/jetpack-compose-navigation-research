package ru.hh.navigation.voyager.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.hh.navigation.common.FlowScreenContainer
import ru.hh.navigation.common.STEPS_COUNT
import ru.hh.navigation.common.randomString

internal val LocalOpenNextStep = compositionLocalOf<() -> Unit> { error("not provided") }
internal val LocalFinishFlow = compositionLocalOf<() -> Unit> { error("not provided") }

internal class FlowNavigationScreen(
    private val firstScreenTitle: String,
) : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        var step by rememberSaveable { mutableStateOf(1) }
        FlowScreenContainer(step = step, stepsCount = STEPS_COUNT) {
            val isOnNextEnabled by remember {
                derivedStateOf { step < STEPS_COUNT }
            }
            val navigator = LocalNavigator.currentOrThrow
            val finish by rememberUpdatedState { navigator.pop() }
            Navigator(
                screen = StepScreen(firstScreenTitle, isOnNextEnabled = isOnNextEnabled),
                onBackPressed = {
                    step--
                    true
                }
            ) { navigator ->
                CompositionLocalProvider(
                    LocalOpenNextStep provides {
                        step++
                        navigator.push(StepScreen(randomString(), isOnNextEnabled = isOnNextEnabled))
                    },
                    LocalFinishFlow provides { finish() }
                ) {
                    CurrentScreen()
                }
            }
        }
    }

}
