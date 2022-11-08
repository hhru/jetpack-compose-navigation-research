package ru.hh.navigation.modo.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import com.github.terrakok.modo.Modo

internal val LocalShowNotSupported = staticCompositionLocalOf<() -> Unit> { {} }

internal class ModoRootFragment : Fragment() {

    private var rootScreen: ModoStackScreen? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            rootScreen = Modo.init(savedInstanceState, rootScreen) { ModoStackScreen() }
            setContent {
                MaterialTheme {
                    val context = LocalContext.current
                    CompositionLocalProvider(
                        LocalShowNotSupported provides {
                            Toast.makeText(context, "Not supported", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        rootScreen?.Content()
                    }
                }
            }
        }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Modo.save(outState, rootScreen)
    }

}
