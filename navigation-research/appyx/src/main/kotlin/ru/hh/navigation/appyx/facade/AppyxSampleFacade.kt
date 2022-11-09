package ru.hh.navigation.appyx.facade

import ru.hh.di.EmptyDeps
import ru.hh.di.FeatureFacade
import toothpick.ktp.binding.module

class AppyxSampleFacade : FeatureFacade<AppyxDeps, AppyxSampleApi>(
    depsClass = AppyxDeps::class.java,
    apiClass = AppyxSampleApi::class.java,
    featureScopeName = "APPYX_SAMPLE",
    featureScopeModule = {
        module { }
    }
)
