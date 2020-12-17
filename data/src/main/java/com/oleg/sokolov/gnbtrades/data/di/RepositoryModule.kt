package com.oleg.sokolov.gnbtrades.data.di

import com.oleg.sokolov.gnbtrades.data.repository.RatesRepositoryImpl
import com.oleg.sokolov.gnbtrades.data.repository.TransactionsRepositoryImpl
import com.oleg.sokolov.gnbtrades.domain.repository.RatesRepository
import com.oleg.sokolov.gnbtrades.domain.repository.TransactionsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideTransactionRepository(
        transactionsRepository: TransactionsRepositoryImpl
    ): TransactionsRepository

    @Binds
    abstract fun provideRatesRepository(
        ratesRepository: RatesRepositoryImpl
    ): RatesRepository

}