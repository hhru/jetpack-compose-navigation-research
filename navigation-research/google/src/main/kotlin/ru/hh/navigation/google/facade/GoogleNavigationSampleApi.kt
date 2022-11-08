package ru.hh.navigation.google.facade

import androidx.fragment.app.Fragment
import ru.hh.navigation.google.presentation.GoogleNavigationRootFragment
import toothpick.InjectConstructor

@InjectConstructor
class GoogleNavigationSampleApi {
    fun getNavFragment(): Fragment = GoogleNavigationRootFragment()
}
