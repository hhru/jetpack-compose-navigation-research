package ru.hh.navigation.google.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import ru.hh.navigation.common.FlowScreenContainer
import ru.hh.navigation.common.STEPS_COUNT
import ru.hh.navigation.common.StepContent
import ru.hh.navigation.common.randomString
import java.util.UUID

@OptIn(ExperimentalAnimationApi::class)
@Suppress("LongMethod")
internal fun NavGraphBuilder.flowGraphWithoutHeader(navController: NavHostController) {
    navigation(
        route = Screens.flowWithoutHeader,
        startDestination = "step/{step}?{text}"
    ) {
        composable(
            route = "step/{step}?{text}",
            arguments = listOf(
                navArgument("text") {
                    defaultValue = "default text"
                    type = NavType.StringType
                },
                navArgument("step") {
                    defaultValue = 1
                }
            )
        ) {
            val step = it.arguments!!.getInt("step")
            StepContent(
                text = it.arguments!!.getString("text")!!,
                isOnNextEnabled = step < STEPS_COUNT,
                onNextClick = {
                    navController.navigate("step/${step + 1}?${UUID.randomUUID()}")
                },
                onFinishFlowClick = {
//                    navController.popBackStackByCondition()
                    navController.popBackStackByDestination()
                }
            )
        }
    }
}

// Для использования замените вызов popBackStackByDestination на этот метод.
@Suppress("UnusedPrivateMember")
private fun NavController.popBackStackByCondition() {
    val backTo = backQueue.findLast {
        !(it.destination.route?.startsWith("step/") ?: false)
    }
    popBackStack(destinationId = backTo!!.destination.id, inclusive = false)
}

private fun NavController.popBackStackByDestination() {
    popBackStack(route = Screens.flowWithoutHeader, inclusive = true)
}

@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.flowGraphWithHeader(navController: NavHostController) {
    composable(route = Screens.flowWithHeader) {
        FlowScreen(onFinishFlowClick = navController::popBackStack)
    }
}

@Suppress("LongMethod")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FlowScreen(
    onFinishFlowClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberAnimatedNavController()
    val progressStep = rememberSaveable { mutableStateOf(1) }
    val startText = rememberSaveable { randomString() }
    FlowScreenContainer(
        step = progressStep.value,
        stepsCount = STEPS_COUNT,
        modifier = modifier
    ) {
        // есть ли способ сделать то же самое через подписку на состояние?
        BackHandler(enabled = navController.backQueue.size > 1) {
            progressStep.value--
            navController.popBackStack()
        }
        AnimatedNavHost(
            navController = navController,
            startDestination = "step/{step}/{text}"
        ) {
            composable(
                route = "step/{step}/{text}",
                arguments = listOf(
                    navArgument("text") {
                        defaultValue = startText
                        type = NavType.StringType
                    },
                    navArgument("step") {
                        defaultValue = 1
                    }
                )
            ) {
                val step = it.arguments!!.getInt("step")
                StepContent(
                    text = it.arguments!!.getString("text")!!,
                    isOnNextEnabled = step < STEPS_COUNT,
                    onNextClick = {
                        progressStep.value++
                        navController.navigate("step/${step + 1}/${randomString()}")
                    },
                    onFinishFlowClick = onFinishFlowClick
                )
            }
        }
    }
}
