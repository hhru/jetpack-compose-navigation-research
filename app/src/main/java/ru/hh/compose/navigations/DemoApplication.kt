package ru.hh.compose.navigations

import android.app.Application
import ru.hh.compose.navigations.di.DemoDi
import timber.log.Timber
import toothpick.Toothpick
import toothpick.configuration.Configuration

internal class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(PrettyLoggingTree(getString(R.string.app_name)))
        initTp()
    }

    private fun initTp() {
        val tpConfig = Configuration.forDevelopment().preventMultipleRootScopes()
        Toothpick.setConfiguration(tpConfig)
        DemoDi.initAppScope()
    }

}
