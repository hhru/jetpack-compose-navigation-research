package ru.hh.compose.navigations.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import toothpick.config.Module
import toothpick.ktp.binding.bind

internal class DemoRootNavigationModule : Module() {

    private val cicerone = Cicerone.create(Router())

    init {
        bind<Router>().toInstance(cicerone.router)
        bind<NavigatorHolder>().toInstance(cicerone.getNavigatorHolder())
    }

}
