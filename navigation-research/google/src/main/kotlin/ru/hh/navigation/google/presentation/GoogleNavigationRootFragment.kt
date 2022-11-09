package ru.hh.navigation.google.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.dialog
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.*
import ru.hh.navigation.common.DialogContent
import ru.hh.navigation.common.MultiStackScreenContent
import ru.hh.navigation.common.TABS
import ru.hh.navigation.common.randomString
import ru.hh.navigation.google.facade.GoogleDeps
import ru.hh.navigation.google.facade.GoogleNavigationSampleFacade
import toothpick.ktp.extension.getInstance

internal class GoogleNavigationRootFragment : Fragment() {

    @OptIn(ExperimentalMaterialNavigationApi::class)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    NavGraph()
                }
            }
        }

    @Suppress("LongMethod")
    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    @Composable
    private fun NavGraph(
        modifier: Modifier = Modifier,
        bottomSheetNavigator: BottomSheetNavigator = rememberBottomSheetNavigator(),
        navController: NavHostController = rememberAnimatedNavController(bottomSheetNavigator),
    ) {
        // пример самой базовой интеграции навигации
//        val navController = rememberNavController()
//        NavHost(
//            navController = navController,
//            startDestination = "screenNew?{title}"
//        ) {
//            // пример того, как бы это выглядело без выноса строчек
//            composable(
//                route = "screenNew?{title}",
//                arguments = listOf(
//                    navArgument("title") {
//                        type = NavType.StringType
//                        defaultValue = "default arg"
//                    }
//                )
//            ) {
//                SampleScreenContent(navController, it)
//            }
//        }
        ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
            AnimatedNavHost(
                navController = navController,
                startDestination = Screens.Sample.route,
                modifier = modifier,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween()
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween()
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween()
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween()
                    )
                }
            ) {
                composable(
                    route = Screens.Sample.route,
                    arguments = listOf(
                        navArgument(Screens.Sample.titleArg) {
                            type = NavType.StringType
                            defaultValue = "default arg"
                        }
                    )
                ) {
                    SampleScreenContent(navController, it)
                }
                flowGraphWithoutHeader(navController)
                flowGraphWithHeader(navController)
                multiScreenGraph(modifier)
                bottomSheet(route = Screens.bottomSheet) {
                    SampleScreenContent(navController, it)
                }
                dialog(
                    route = Screens.Dialog.route,
                    arguments = listOf(
                        navArgument(Screens.Sample.titleArg) {
                            type = NavType.StringType
                            defaultValue = "default arg"
                        }
                    )
                ) {
                    DialogContent(
                        openDialog = { navController.navigate(Screens.Dialog.destination(randomString())) },
                        openScreen = { navController.openSample() },
                        closeDialog = { navController.popBackStack() },
                        screenTitle = it.arguments?.getString(Screens.Sample.titleArg)
                    )
                }
            }
        }
    }

    @Composable
    private fun SampleScreenContent(
        navController: NavHostController,
        it: NavBackStackEntry,
    ) {
        ru.hh.navigation.common.SampleScreenContent(
            openScreen = { navController.navigate("screen?{${randomString()}}") },
            openBottomSheet = { navController.navigate(Screens.bottomSheet) },
            openDialog = {
                navController.navigate(Screens.Dialog.destination(randomString()))
            },
            startFlowWithHeader = { navController.navigate(Screens.flowWithHeader) },
            startFlowWithoutHeader = {
                navController.navigate(Screens.flowWithoutHeader)
            },
            openMultiscreen = {
                navController.navigate(Screens.multiScreen)
            },
            openScreenModel = {
                navController.navigate(Screens.Sample.destination(null))
            },
            openComplexNavigation = { },
            openFragment = {
                GoogleNavigationSampleFacade().featureScope.getInstance<GoogleDeps>().openFragment()
            },
            screenTitle = it.arguments?.getString(Screens.Sample.titleArg),
            modifier = Modifier.fillMaxSize()
        )
    }

    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    private fun NavGraphBuilder.multiScreenGraph(modifier: Modifier) {
        composable(Screens.multiScreen) {
            val multiScreenController = rememberAnimatedNavController()
            val firstPos = 0
            var pos by rememberSaveable {
                mutableStateOf(firstPos)
            }
            MultiStackScreenContent(
                selectedPos = pos,
                onTabClick = {
                    pos = it
                    // код из документации. По факту у нас стэк, но вот таким костылём мы сохраняем состояние экранов
                    multiScreenController.navigate(TABS[it].second) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(multiScreenController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                modifier = modifier
            ) {
                AnimatedNavHost(
                    navController = multiScreenController,
                    startDestination = TABS[firstPos].second,
                    modifier = Modifier.padding(it)
                ) {
                    for (i in 0..2) {
                        composable(TABS[i].second) {
                            NavGraph()
                        }
                    }
                }
            }
        }
    }

}

internal fun NavHostController.openSample() {
    navigate(Screens.Sample.destination(randomString()))
}

internal object Screens {

    object Sample {

        const val titleArg = "title"
        const val baseRoute = "screen"
        const val route = "$baseRoute?{$titleArg}"
        fun destination(title: String?) = "$baseRoute?${title.orEmpty()}"
    }

    object Dialog {

        const val titleArg = "title"
        const val baseRoute = "dialog"
        const val route = "$baseRoute?{$titleArg}"
        fun destination(title: String?) = "$baseRoute?${title.orEmpty()}"
    }

    const val bottomSheet = "bottomSheet"
    const val flowWithoutHeader = "flowWithoutHeader"
    const val flowWithHeader = "flowWithHeader"
    const val multiScreen = "multiScreen"

}
