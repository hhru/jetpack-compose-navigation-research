package ru.hh.navigation.appyx.facade

import ru.hh.di.EmptyDeps
import ru.hh.di.FeatureFacade
import toothpick.ktp.binding.module

class AppyxSampleFacade : FeatureFacade<EmptyDeps, AppyxSampleApi>(
    depsClass = EmptyDeps::class.java,
    apiClass = AppyxSampleApi::class.java,
    featureScopeName = "APPYX_SAMPLE",
    featureScopeModule = {
        module { }
    }
)
