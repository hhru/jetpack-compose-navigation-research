package ru.hh.compose.navigations.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun Router.openScreen(factory: () -> Fragment) {
    navigateTo(
        object : FragmentScreen {
            override fun createFragment(factory: FragmentFactory): Fragment = factory()
        }
    )
}