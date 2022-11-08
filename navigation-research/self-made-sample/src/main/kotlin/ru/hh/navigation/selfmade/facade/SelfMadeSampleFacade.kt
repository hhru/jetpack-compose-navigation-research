package ru.hh.navigation.selfmade.facade

import ru.hh.di.EmptyDeps
import ru.hh.di.FeatureFacade
import toothpick.ktp.binding.module

class SelfMadeSampleFacade : FeatureFacade<EmptyDeps, SelfMadeSampleApi>(
    depsClass = EmptyDeps::class.java,
    apiClass = SelfMadeSampleApi::class.java,
    featureScopeName = "SELF_MADE_SAMPLE",
    featureScopeModule = {
        module {}
    }
)
