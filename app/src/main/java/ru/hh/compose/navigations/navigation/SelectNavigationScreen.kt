package ru.hh.compose.navigations.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.hh.compose.navigations.SelectNavigationFragment

internal class SelectNavigationScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = SelectNavigationFragment()
}
