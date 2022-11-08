package ru.hh.navigation.modo.facade

import ru.hh.di.EmptyDeps
import ru.hh.di.FeatureFacade
import toothpick.ktp.binding.module

class ModoSampleFacade : FeatureFacade<EmptyDeps, ModoSampleApi>(
    depsClass = EmptyDeps::class.java,
    apiClass = ModoSampleApi::class.java,
    featureScopeName = "MODO_SAMPLE",
    featureScopeModule = {
        module { }
    }
)
