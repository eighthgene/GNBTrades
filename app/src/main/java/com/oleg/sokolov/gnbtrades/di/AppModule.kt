package com.oleg.sokolov.gnbtrades.di

import com.oleg.sokolov.gnbtrades.data.common.coroutine.CoroutineContextProvider
import org.koin.dsl.module

val appModule = module {

    single { CoroutineContextProvider() }

}