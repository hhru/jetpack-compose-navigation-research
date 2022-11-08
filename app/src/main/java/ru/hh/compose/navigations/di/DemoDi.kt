package ru.hh.compose.navigations.di

import ru.hh.compose.navigations.di.deps.FeatureDepsModule
import ru.hh.compose.navigations.navigation.DemoRootNavigationModule
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration

internal object DemoDi {

    init {
        Toothpick.setConfiguration(Configuration.forDevelopment())
    }

    fun openAppScope(): Scope = Toothpick.openScope("APP_SCOPE")

    fun initAppScope() {
        openAppScope()
            .installModules(
                DemoRootNavigationModule(),
                FeatureDepsModule()
            )
    }

}
