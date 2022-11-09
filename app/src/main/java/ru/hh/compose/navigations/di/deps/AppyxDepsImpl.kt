package ru.hh.compose.navigations.di.deps

import com.github.terrakok.cicerone.Router
import ru.hh.compose.navigations.navigation.openScreen
import ru.hh.navigation.appyx.facade.AppyxDeps
import ru.hh.navigation.appyx.facade.AppyxSampleFacade
import toothpick.InjectConstructor

@InjectConstructor
class AppyxDepsImpl(private val router: Router) : AppyxDeps {
    override fun openFragment() = router.openScreen(AppyxSampleFacade().api::getNavFragment)
}