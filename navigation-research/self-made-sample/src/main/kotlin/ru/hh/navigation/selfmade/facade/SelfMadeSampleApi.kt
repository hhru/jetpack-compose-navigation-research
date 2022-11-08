package ru.hh.navigation.selfmade.facade

import androidx.fragment.app.Fragment
import ru.hh.navigation.selfmade.presentation.SelfMadeSampleRootFragment
import toothpick.InjectConstructor

@InjectConstructor
class SelfMadeSampleApi {
    fun getNavFragment(): Fragment = SelfMadeSampleRootFragment()
}
