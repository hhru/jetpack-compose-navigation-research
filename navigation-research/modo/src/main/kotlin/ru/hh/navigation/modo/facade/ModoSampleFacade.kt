package ru.hh.navigation.modo.facade

import ru.hh.di.FeatureFacade
import toothpick.ktp.binding.module

class ModoSampleFacade : FeatureFacade<ModoDeps, ModoSampleApi>(
    depsClass = ModoDeps::class.java,
    apiClass = ModoSampleApi::class.java,
    featureScopeName = "MODO_SAMPLE",
    featureScopeModule = {
        module { }
    }
)
