package ru.hh.navigation.voyager.facade

import androidx.fragment.app.Fragment
import ru.hh.navigation.voyager.presentation.VoyagerRootFragment
import toothpick.InjectConstructor

@InjectConstructor
class VoyagerSampleApi {
    fun getNavFragment(): Fragment = VoyagerRootFragment()
}
