package com.oleg.sokolov.gnbtrades.di

import com.oleg.sokolov.gnbtrades.data.repository.RatesRepositoryImpl
import com.oleg.sokolov.gnbtrades.data.repository.TransactionsRepositoryImpl
import com.oleg.sokolov.gnbtrades.domain.repository.RatesRepository
import com.oleg.sokolov.gnbtrades.domain.repository.TransactionsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun provideTransactionRepository(
        transactionsRepository: TransactionsRepositoryImpl
    ): TransactionsRepository

    @ActivityRetainedScoped
    @Binds
    abstract fun provideRatesRepository(
        ratesRepository: RatesRepositoryImpl
    ): RatesRepository

}