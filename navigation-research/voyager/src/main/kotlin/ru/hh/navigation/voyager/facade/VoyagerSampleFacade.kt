package ru.hh.navigation.voyager.facade

import ru.hh.di.FeatureFacade
import toothpick.ktp.binding.module

class VoyagerSampleFacade : FeatureFacade<VoyagerDeps, VoyagerSampleApi>(
    depsClass = VoyagerDeps::class.java,
    apiClass = VoyagerSampleApi::class.java,
    featureScopeName = "VOYAGER_SAMPLE",
    featureScopeModule = {
        module { }
    }
)
