package ru.hh.navigation.modo.facade

import androidx.fragment.app.Fragment
import ru.hh.navigation.modo.presentation.ModoRootFragment
import toothpick.InjectConstructor

@InjectConstructor
class ModoSampleApi {
    fun getNavFragment(): Fragment = ModoRootFragment()
}
