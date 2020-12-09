package com.oleg.sokolov.gnbtrades.core.base.data

import com.oleg.sokolov.gnbtrades.data.repository.TransactionsRepositoryImpl
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

}