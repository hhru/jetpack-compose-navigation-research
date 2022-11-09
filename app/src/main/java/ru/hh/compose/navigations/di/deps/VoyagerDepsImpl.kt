package ru.hh.compose.navigations.di.deps

import com.github.terrakok.cicerone.Router
import ru.hh.compose.navigations.navigation.openScreen
import ru.hh.navigation.voyager.facade.VoyagerDeps
import ru.hh.navigation.voyager.facade.VoyagerSampleFacade
import toothpick.InjectConstructor

@InjectConstructor
class VoyagerDepsImpl(private val router: Router) : VoyagerDeps {
    override fun openFragment() = router.openScreen(VoyagerSampleFacade().api::getNavFragment)
}