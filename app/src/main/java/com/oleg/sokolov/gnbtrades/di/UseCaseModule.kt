package com.oleg.sokolov.gnbtrades.di

import com.oleg.sokolov.gnbtrades.domain.interaction.products.GetProductListUseCase
import com.oleg.sokolov.gnbtrades.domain.interaction.products.impl.GetProductListUseCaseImpl
import com.oleg.sokolov.gnbtrades.domain.interaction.rates.GetRatesUseCase
import com.oleg.sokolov.gnbtrades.domain.interaction.rates.impl.GetRatesUseCaseImpl
import com.oleg.sokolov.gnbtrades.domain.interaction.transactions.GetTransactionsUseCase
import com.oleg.sokolov.gnbtrades.domain.interaction.transactions.impl.GetTransactionsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class UseCaseModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun provideGetProductListUseCase(getProductListUseCaseImpl: GetProductListUseCaseImpl): GetProductListUseCase

    @ActivityRetainedScoped
    @Binds
    abstract fun provideGetTransactionsUseCase(getTransactionsUseCase: GetTransactionsUseCaseImpl): GetTransactionsUseCase

    @ActivityRetainedScoped
    @Binds
    abstract fun provideGetRatesUseCase(getRatesUseCase: GetRatesUseCaseImpl): GetRatesUseCase

}