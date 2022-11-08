package ru.hh.compose.navigations.root

import android.content.Context
import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import ru.hh.compose.navigations.R
import ru.hh.compose.navigations.di.DemoDi
import ru.hh.compose.navigations.navigation.SelectNavigationScreen
import ru.hh.di.DiFragmentPlugin

internal class RootFragment : Fragment(R.layout.fragment_demo_root_container) {

    private val di = DiFragmentPlugin(
        fragment = this,
        parentScope = { DemoDi.openAppScope() }
    )

    private lateinit var navigator: AppNavigator

    private val router: Router by lazy(di::get)
    private val navigatorHolder: NavigatorHolder by lazy(di::get)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            router.newChain(SelectNavigationScreen())
        }
        navigator = AppNavigator(
            requireActivity(),
            R.id.fragment_demo_root_container,
            childFragmentManager,
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if(childFragmentManager.backStackEntryCount <= 1) {
                requireActivity().finish()
            } else {
                router.exit()
            }
        }
    }


    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}