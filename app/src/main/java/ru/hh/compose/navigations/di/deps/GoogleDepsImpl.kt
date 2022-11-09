package ru.hh.compose.navigations.di.deps

import com.github.terrakok.cicerone.Router
import ru.hh.compose.navigations.navigation.openScreen
import ru.hh.navigation.appyx.facade.AppyxSampleFacade
import ru.hh.navigation.google.facade.GoogleDeps
import ru.hh.navigation.google.facade.GoogleNavigationSampleFacade
import toothpick.InjectConstructor

@InjectConstructor
class GoogleDepsImpl(private val router: Router) : GoogleDeps {
    override fun openFragment() = router.openScreen(GoogleNavigationSampleFacade().api::getNavFragment)
}