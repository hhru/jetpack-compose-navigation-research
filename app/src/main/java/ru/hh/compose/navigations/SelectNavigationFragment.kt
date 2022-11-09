package ru.hh.compose.navigations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.hh.compose.navigations.di.DemoDi
import ru.hh.compose.navigations.navigation.openScreen
import ru.hh.di.DiFragmentPlugin
import ru.hh.navigation.appyx.facade.AppyxSampleFacade
import ru.hh.navigation.google.facade.GoogleNavigationSampleFacade
import ru.hh.navigation.modo.facade.ModoSampleFacade
import ru.hh.navigation.selfmade.facade.SelfMadeSampleFacade
import ru.hh.navigation.voyager.facade.VoyagerSampleFacade

internal class SelectNavigationFragment : Fragment() {

    private val di = DiFragmentPlugin(
        this,
        parentScope = { DemoDi.openAppScope() }
    )

    private val router: Router by lazy { di.get() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    SelectNavigationContent(
                        openAppex = {
                            router.openScreen(AppyxSampleFacade().api::getNavFragment)
                        },
                        openGoogle = {
                            router.openScreen(GoogleNavigationSampleFacade().api::getNavFragment)
                        },
                        openVoyager = {
                            router.openScreen(VoyagerSampleFacade().api::getNavFragment)
                        },
                        openSelfMade = {
                            router.openScreen(SelfMadeSampleFacade().api::getNavFragment)
                        },
                        openModo = {
                            router.openScreen(ModoSampleFacade().api::getNavFragment)
                        },
                    )
                }
            }
        }

}


@Composable
private fun SelectNavigationContent(
    openVoyager: () -> Unit,
    openAppex: () -> Unit,
    openGoogle: () -> Unit,
    openModo: () -> Unit,
    openSelfMade: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .align(Alignment.Center)
                .width(IntrinsicSize.Max),
            Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = openSelfMade
            ) {
                Text(text = "Self made")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = openGoogle
            ) {
                Text(text = "Google navigation")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = openAppex
            ) {
                Text(text = "Appyx")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = openVoyager
            ) {
                Text(text = "Voyager")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = openModo
            ) {
                Text(text = "Modo")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSelectNavigation() {
    SelectNavigationContent({}, {}, {}, {}, {})
}