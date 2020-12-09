package com.oleg.sokolov.gnbtrades.di

import android.content.Context
import com.oleg.sokolov.gnbtrades.core.network.Connectivity
import com.oleg.sokolov.gnbtrades.core.network.ConnectivityImpl
import com.oleg.sokolov.gnbtrades.data.TransactionsApi
import com.oleg.sokolov.gnbtrades.data.networking.ResponseAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(ResponseAdapter()).build()

    @Provides
    @Singleton
    fun provideRetrofit(adapter: Moshi ,httpClient: OkHttpClient): Retrofit =
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
    fun provideConnectivity(@ApplicationContext context: Context): Connectivity =
        ConnectivityImpl(context)

    @Singleton
    @Provides
    fun provideTransactionsApi(retrofit: Retrofit): TransactionsApi = retrofit.create(TransactionsApi::class.java)

}