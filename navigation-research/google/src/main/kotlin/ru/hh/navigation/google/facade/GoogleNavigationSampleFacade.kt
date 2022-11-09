package ru.hh.navigation.google.facade

import ru.hh.di.EmptyDeps
import ru.hh.di.FeatureFacade
import toothpick.ktp.binding.module

class GoogleNavigationSampleFacade : FeatureFacade<GoogleDeps, GoogleNavigationSampleApi>(
    depsClass = GoogleDeps::class.java,
    apiClass = GoogleNavigationSampleApi::class.java,
    featureScopeName = "GOOGLE_NAVIGATION_SAMPLE",
    featureScopeModule = {
        module {}
    }
)
