package ru.hh.compose.navigations.di.deps

import ru.hh.di.EmptyDeps
import ru.hh.di.FeatureFacade
import ru.hh.navigation.appyx.facade.AppyxSampleFacade
import ru.hh.navigation.google.facade.GoogleNavigationSampleFacade
import ru.hh.navigation.modo.facade.ModoSampleFacade
import ru.hh.navigation.voyager.facade.VoyagerSampleFacade
import toothpick.config.Module
import toothpick.ktp.binding.bind
import kotlin.reflect.KClass

/**
 * Здесь происходит описание связей для склейки фичемодулей
 */
internal class FeatureDepsModule : Module() {

    init {
        bind<EmptyDeps>().toInstance { object : EmptyDeps {} }
        bindFeatureDeps(ModoSampleFacade(), ModoDepsImpl::class)
        bindFeatureDeps(VoyagerSampleFacade(), VoyagerDepsImpl::class)
        bindFeatureDeps(GoogleNavigationSampleFacade(), GoogleDepsImpl::class)
        bindFeatureDeps(AppyxSampleFacade(), AppyxDepsImpl::class)
    }

    private inline fun <reified Deps : Any, Api : Any, reified DepsImpl : Deps, reified TFeatureFacade : FeatureFacade<Deps, Api>> bindFeatureDeps(
        featureFacade: TFeatureFacade,
        depsImpl: KClass<DepsImpl>
    ) {
        // FeatureFacade -- абстракция над Api + Deps фичемодуля
        // Задаем связь Deps фичемодуля с DepsImpl и предоставляем Api фичемодуля

        bind<Deps>().toClass(depsImpl).singleton()
        bind<TFeatureFacade>().toInstance(featureFacade)
    }

}