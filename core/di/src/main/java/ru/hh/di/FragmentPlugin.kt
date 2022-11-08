package ru.hh.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import toothpick.Scope
import toothpick.Toothpick
import toothpick.config.Module
import toothpick.ktp.binding.bind

/**
 * Создает и поддерживает Toothpick-скоуп, который автоматически связан с жизненным циклом фрагмента
 *
 * @param fragment      Фрагмент, жизненный цикл которого используется для скоупа
 * @param parentScope   Предоставляет родительский скоуп (этом может быть FeatureScope или скоуп родительского фрагмента)
 * @param scopeNameSuffix   Дополнительный суффикс для имени скоупа при открытии одинаковых фрагментов
 * @param scopeModules  Список Toothpick-модулей, которые будут предоставлены для скоупа фрагмента
 */
class DiFragmentPlugin(
    private val fragment: Fragment,
    private val parentScope: () -> Scope,
    private val scopeNameSuffix: () -> String = { "" },
    private val scopeModules: () -> Array<Module> = { emptyArray() }
) : DefaultLifecycleObserver {

    private val scopeName by lazy { "${fragment.javaClass.name}_${scopeNameSuffix()}" }

    /**
     * Для каждого скоупа фрагмента создаем Disposable, который будет закрыт при уничтожении скоупа.
     */
    private val coroutineScope: CoroutineScope = MainScope()

    init {
        fragment.lifecycle.addObserver(this)
    }

    val scope: Scope by lazy {
        if (!Toothpick.isScopeOpen(scopeName)) {
            parentScope().openSubScope(scopeName).apply {
                installModules(*scopeModules(), object : Module() {
                    init {
                        bind<CoroutineScope>().toInstance(coroutineScope)
                    }
                })
            }
        } else {
            Toothpick.openScope(scopeName)
        }
    }

    inline fun <reified T> get(): T = scope.getInstance(T::class.java)

    override fun onDestroy(owner: LifecycleOwner) {
        if (!fragment.requireActivity().isChangingConfigurations) {
            coroutineScope.cancel()
            Toothpick.closeScope(scopeName)
        }
    }

}
