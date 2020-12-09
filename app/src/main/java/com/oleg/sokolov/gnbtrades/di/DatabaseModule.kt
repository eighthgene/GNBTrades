package com.oleg.sokolov.gnbtrades.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oleg.sokolov.gnbtrades.data.TransactionsApi
import com.oleg.sokolov.gnbtrades.data.database.TransactionsDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Singleton

private const val TRANSACTIONS_DB = "transactions-database"

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TransactionsDatabase::class.java, TRANSACTIONS_DB)
            .fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideTransactionDao(transactionsDatabase: TransactionsDatabase) =
        transactionsDatabase.transactionDao()

}