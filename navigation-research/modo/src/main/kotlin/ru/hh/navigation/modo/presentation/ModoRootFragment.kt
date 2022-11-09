package ru.hh.navigation.modo.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.github.terrakok.modo.Modo

internal val LocalShowNotSupported = staticCompositionLocalOf<() -> Unit> { {} }

internal class ModoRootFragment : Fragment() {

    private var rootScreen: ModoStackScreen? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootScreen = Modo.init(savedInstanceState, rootScreen) { ModoStackScreen() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                rootScreen?.Content()
            }
        }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Modo.save(outState, rootScreen)
    }

}
