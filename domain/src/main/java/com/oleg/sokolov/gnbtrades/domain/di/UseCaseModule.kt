package com.oleg.sokolov.gnbtrades.domain.di

import com.oleg.sokolov.gnbtrades.domain.interaction.products.GetProductListUseCase
import com.oleg.sokolov.gnbtrades.domain.interaction.products.impl.GetProductListUseCaseImpl
import com.oleg.sokolov.gnbtrades.domain.interaction.rates.GetRatesUseCase
import com.oleg.sokolov.gnbtrades.domain.interaction.rates.impl.GetRatesUseCaseImpl
import com.oleg.sokolov.gnbtrades.domain.interaction.transactions.GetTransactionsUseCase
import com.oleg.sokolov.gnbtrades.domain.interaction.transactions.impl.GetTransactionsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

    @Binds
    abstract fun provideGetProductListUseCase(getProductListUseCaseImpl: GetProductListUseCaseImpl): GetProductListUseCase

    @Binds
    abstract fun provideGetTransactionsUseCase(getTransactionsUseCase: GetTransactionsUseCaseImpl): GetTransactionsUseCase

    @Binds
    abstract fun provideGetRatesUseCase(getRatesUseCase: GetRatesUseCaseImpl): GetRatesUseCase

}