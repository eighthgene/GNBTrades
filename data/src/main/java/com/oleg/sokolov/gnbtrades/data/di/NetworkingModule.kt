package com.oleg.sokolov.gnbtrades.data.di

import com.oleg.sokolov.gnbtrades.data.GNBankApi
import com.oleg.sokolov.gnbtrades.data.common.utils.Connectivity
import com.oleg.sokolov.gnbtrades.data.common.utils.ConnectivityImpl
import com.oleg.sokolov.gnbtrades.data.networking.TransactionsResponseAdapter
import com.oleg.sokolov.gnbtrades.data.networking.model.RatesResponseAdapter
import com.squareup.moshi.Moshi

import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "http://quiet-stone-2094.herokuapp.com"

val networkingModule = module {

    single {
        Moshi.Builder()
            .add(TransactionsResponseAdapter())
            .add(RatesResponseAdapter())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single {
        OkHttpClient()
            .newBuilder()
            .build()
    }

    single<Connectivity> {
        ConnectivityImpl(androidContext())
    }

    single {
        get<Retrofit>().create(GNBankApi::class.java)
    }

}