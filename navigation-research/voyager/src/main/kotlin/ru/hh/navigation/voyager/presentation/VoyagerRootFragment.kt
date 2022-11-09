package ru.hh.navigation.voyager.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import ru.hh.navigation.common.randomString

internal val LocalShowToast = staticCompositionLocalOf<() -> Unit> { {} }

internal class VoyagerRootFragment : Fragment() {

    @OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    val context = LocalContext.current
                    CompositionLocalProvider(
                        LocalShowToast provides {
                            Toast.makeText(context, "Not supported", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        BottomSheetNavigator {
                            Navigator(screen = SampleScreen(randomString())) {
                                SlideTransition(navigator = it)
                            }
                        }
                    }
                }
            }
        }

}
