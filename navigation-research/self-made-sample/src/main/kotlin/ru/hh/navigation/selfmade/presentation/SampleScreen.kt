package ru.hh.navigation.selfmade.presentation

import android.os.Parcelable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.parcelize.Parcelize
import ru.hh.navigation.common.ScreenWithButtons
import ru.hh.navigation.common.randomBackground
import java.util.*

@Composable
internal fun SelfMadeNavigationSample() {
    FixedSelfMadeNavigation()
//    SelfMadeNavigation()
}

@Composable
internal fun SelfMadeNavigation() {
    var navigationState: NavigationState<ScreenState> by rememberSaveable {
        mutableStateOf(NavigationState(listOf(ScreenState(1))))
    }
    val renderState = navigationState.stack.last()
    BackHandler(enabled = navigationState.stack.size > 1) {
        navigationState = navigationState.copy(
            stack = navigationState.stack.dropLast(1)
        )
    }
    ScreenWithButtons(
        buttons = listOf(
            "Open Screen" to {
                navigationState = navigationState.copy(
                    stack = navigationState.stack + ScreenState(renderState.screenPos + 1)
                )
            },
        ),
        screenTitle = "Stack position = ${renderState.screenPos}",
        modifier = Modifier
            .randomBackground()
            .fillMaxSize()
    )
}

@Composable
internal fun FixedSelfMadeNavigation() {
    var navigationState: NavigationState<FixedScreenState> by rememberSaveable {
        mutableStateOf(NavigationState(listOf(FixedScreenState(1))))
    }
    val renderState = navigationState.stack.last()
    BackHandler(enabled = navigationState.stack.size > 1) {
        navigationState = navigationState.copy(
            stack = navigationState.stack.dropLast(1)
        )
    }
    // TODO: очищать saveable state holder!
    val saveableStateHolder = rememberSaveableStateHolder()
    saveableStateHolder.SaveableStateProvider(key = renderState.key) {
        ScreenWithButtons(
            buttons = listOf(
                "Open Screen" to {
                    navigationState = navigationState.copy(
                        stack = navigationState.stack + FixedScreenState(renderState.screenPos + 1)
                    )
                },
            ),
            screenTitle = "Stack position = ${renderState.screenPos}",
            modifier = Modifier
                .randomBackground()
                .fillMaxSize()
        )
    }
}

@Parcelize
internal class FixedScreenState(
    val screenPos: Int,
    val key: String = UUID.randomUUID().toString(),
) : Parcelable

@Parcelize
internal class ScreenState(
    val screenPos: Int,
) : Parcelable

@Parcelize
internal data class NavigationState<T : Parcelable>(
    val stack: List<T>,
) : Parcelable
