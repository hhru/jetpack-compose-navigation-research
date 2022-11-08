package ru.hh.compose.navigations.di.deps

import ru.hh.di.EmptyDeps
import ru.hh.di.FeatureFacade
import toothpick.config.Module
import toothpick.ktp.binding.bind
import kotlin.reflect.KClass


/**
 * Здесь происходит описание связей для склейки фичемодулей
 */
internal class FeatureDepsModule : Module() {

    init {
        bind<EmptyDeps>().toInstance { object : EmptyDeps {} }
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