package com.oleg.sokolov.gnbtrades.data.di

import android.content.Context
import com.oleg.sokolov.gnbtrades.data.GNBankApi
import com.oleg.sokolov.gnbtrades.data.common.utils.Connectivity
import com.oleg.sokolov.gnbtrades.data.common.utils.ConnectivityImpl
import com.oleg.sokolov.gnbtrades.data.networking.TransactionsResponseAdapter
import com.oleg.sokolov.gnbtrades.data.networking.model.RatesResponseAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import javax.inject.Singleton

@Module

object NetworkingModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(TransactionsResponseAdapter())
            .add(RatesResponseAdapter())
            .build()

    @Provides
    @Singleton
    fun provideTransactionsRetrofit(adapter: Moshi, httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://quiet-stone-2094.herokuapp.com")
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(adapter))
            .build()


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .build()

    @Provides
    @Singleton
    fun provideConnectivity(context: Context): Connectivity =
        ConnectivityImpl(context)

    @Singleton
    @Provides
    fun provideTransactionsApi(retrofit: Retrofit): GNBankApi =
        retrofit.create(GNBankApi::class.java)

}