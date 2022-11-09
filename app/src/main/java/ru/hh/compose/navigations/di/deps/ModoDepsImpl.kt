package ru.hh.compose.navigations.di.deps

import com.github.terrakok.cicerone.Router
import ru.hh.compose.navigations.navigation.openScreen
import ru.hh.navigation.modo.facade.ModoDeps
import ru.hh.navigation.modo.facade.ModoSampleFacade
import toothpick.InjectConstructor

@InjectConstructor
class ModoDepsImpl(private val router: Router) : ModoDeps {
    override fun openFragment() = router.openScreen(ModoSampleFacade().api::getNavFragment)
}