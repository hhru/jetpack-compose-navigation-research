package ru.hh.navigation.modo.presentation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import com.github.terrakok.modo.ComposeRendererScope
import com.github.terrakok.modo.DialogScreen
import com.github.terrakok.modo.animation.ScreenTransition
import com.github.terrakok.modo.animation.StackTransitionType
import com.github.terrakok.modo.animation.calculateStackTransitionType
import com.github.terrakok.modo.stack.StackState

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun ComposeRendererScope<StackState>.SlideTransition() {
    ScreenTransition(
        transitionSpec = {
            val transitionType = calculateStackTransitionType()
            val oldDialog = oldState?.stack?.last() as? DialogScreen
            val newDialog = newState?.stack?.last() as? DialogScreen
            when {
                newDialog != null && oldDialog != null -> {
                    fadeIn() with fadeOut()
                }
                transitionType == StackTransitionType.Replace -> {
                    scaleIn(initialScale = 2f) + fadeIn() with fadeOut()
                }
                else -> {
                    val (initialOffset, targetOffset) = when (transitionType) {
                        StackTransitionType.Pop -> ({ size: Int -> -size }) to ({ size: Int -> size })
                        else -> ({ size: Int -> size }) to ({ size: Int -> -size })
                    }
                    slideInHorizontally(initialOffsetX = initialOffset) with
                            slideOutHorizontally(targetOffsetX = targetOffset)
                }
            }
        }
    )
}