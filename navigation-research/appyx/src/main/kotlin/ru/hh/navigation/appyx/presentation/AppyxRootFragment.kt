package ru.hh.navigation.appyx.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.ActivityIntegrationPoint

internal val LocalShowNotSupported = staticCompositionLocalOf<() -> Unit> { {} }

internal class AppyxRootFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                NodeHost(
                    integrationPoint =
                    ActivityIntegrationPoint.getIntegrationPoint(requireContext())
                ) {
                    StackNode(it)
                }
            }
        }

}
