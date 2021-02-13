package com.oleg.sokolov.gnbtrades

import android.app.Application
import com.oleg.sokolov.gnbtrades.data.di.databaseModule
import com.oleg.sokolov.gnbtrades.data.di.networkingModule
import com.oleg.sokolov.gnbtrades.data.di.repositoryModule
import com.oleg.sokolov.gnbtrades.di.appModule
import com.oleg.sokolov.gnbtrades.di.presentationModule
import com.oleg.sokolov.gnbtrades.domain.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            if (BuildConfig.DEBUG) androidLogger(Level.DEBUG)
            modules(appModules + domainModules + dataModules)
        }
    }


}

val appModules = listOf(appModule, presentationModule)
val domainModules = listOf(useCaseModule)
val dataModules = listOf(networkingModule, databaseModule, repositoryModule)