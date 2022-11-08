package ru.hh.navigation.appyx.facade

import androidx.fragment.app.Fragment
import ru.hh.navigation.appyx.presentation.AppyxRootFragment
import toothpick.InjectConstructor

@InjectConstructor
class AppyxSampleApi {
    fun getNavFragment(): Fragment = AppyxRootFragment()
}
