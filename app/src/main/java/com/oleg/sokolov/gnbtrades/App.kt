package com.oleg.sokolov.gnbtrades

import android.app.Application
import com.oleg.sokolov.gnbtrades.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    val component by lazy {
        DaggerAppComponent
            .factory()
            .crete(this)
    }
}