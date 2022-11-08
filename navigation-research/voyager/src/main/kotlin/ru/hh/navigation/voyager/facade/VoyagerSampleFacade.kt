package ru.hh.navigation.voyager.facade

import ru.hh.di.EmptyDeps
import ru.hh.di.FeatureFacade
import toothpick.ktp.binding.module

class VoyagerSampleFacade : FeatureFacade<EmptyDeps, VoyagerSampleApi>(
    depsClass = EmptyDeps::class.java,
    apiClass = VoyagerSampleApi::class.java,
    featureScopeName = "VOYAGER_SAMPLE",
    featureScopeModule = {
        module {  }
    }
)
