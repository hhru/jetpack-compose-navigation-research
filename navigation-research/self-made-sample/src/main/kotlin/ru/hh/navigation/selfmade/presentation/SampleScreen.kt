package ru.hh.navigation.selfmade.presentation

import android.os.Parcelable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import kotlinx.parcelize.Parcelize
import ru.hh.navigation.common.ScreenWithButtons
import ru.hh.navigation.common.randomBackground
import java.util.*

internal val LocalNavigateForward = compositionLocalOf<() -> Unit> { error("no value provided") }

@Composable
internal fun SelfMadeNavigationSample() {
//    FixedSelfMadeNavigation()
    SelfMadeNavigation()
}

@Composable
internal fun SelfMadeNavigation() {
    var navigationState: NavigationState<ScreenState> by rememberSaveable {
        mutableStateOf(NavigationState(listOf(ScreenState(1))))
    }
    BackHandler(enabled = navigationState.stack.size > 1) {
        navigationState = navigationState.copy(
            stack = navigationState.stack.dropLast(1)
        )
    }
    val renderScreen by remember {
        derivedStateOf { navigationState.stack.last() }
    }
    CompositionLocalProvider(
        LocalNavigateForward provides {
            navigationState = navigationState.copy(
                stack = navigationState.stack + ScreenState(renderScreen.screenPos + 1)
            )
        }
    ) {
        renderScreen.Content()
    }
}

@Composable
internal fun FixedSelfMadeNavigation() {
    var navigationState: NavigationState<FixedScreenState> by rememberSaveable {
        mutableStateOf(NavigationState(listOf(FixedScreenState(1))))
    }
    BackHandler(enabled = navigationState.stack.size > 1) {
        navigationState = navigationState.copy(
            stack = navigationState.stack.dropLast(1)
        )
    }
    val renderScreen by remember { derivedStateOf { navigationState.stack.last() } }
    // TODO: очищать saveable state holder!
    val saveableStateHolder = rememberSaveableStateHolder()
    saveableStateHolder.SaveableStateProvider(key = renderScreen.key) {
        CompositionLocalProvider(
            LocalNavigateForward provides {
                navigationState = navigationState.copy(
                    stack = navigationState.stack + FixedScreenState(renderScreen.screenPos + 1)
                )
            }
        ) {
            renderScreen.Content()
        }
    }
}

@Parcelize
internal class FixedScreenState(
    val screenPos: Int,
    val key: String = UUID.randomUUID().toString(),
) : Parcelable {

    @Composable
    fun Content() {
        val navigateForward = LocalNavigateForward.current
        ScreenWithButtons(
            buttons = listOf(
                "Open Screen" to navigateForward,
            ),
            screenTitle = "Stack position = $screenPos",
            modifier = Modifier
                .randomBackground()
                .fillMaxSize()
        )
    }

}

@Parcelize
internal class ScreenState(
    val screenPos: Int,
) : Parcelable {

    @Composable
    fun Content() {
        val navigateForward = LocalNavigateForward.current
        ScreenWithButtons(
            buttons = listOf(
                "Open Screen" to navigateForward,
            ),
            screenTitle = "Stack position = $screenPos",
            modifier = Modifier
                .randomBackground()
                .fillMaxSize()
        )
    }

}

@Parcelize
internal data class NavigationState<T : Parcelable>(
    val stack: List<T>,
) : Parcelable
