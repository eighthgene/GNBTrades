package com.oleg.sokolov.gnbtrades.data.di

import android.content.Context
import androidx.room.Room
import com.oleg.sokolov.gnbtrades.data.database.GNBankDatabase

import dagger.Module
import dagger.Provides

import javax.inject.Singleton

private const val TRANSACTIONS_DB = "transactions-database"

@Module
object DatabaseModule {

    @Provides
    @Singleton
fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, GNBankDatabase::class.java, TRANSACTIONS_DB)
            .fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideTransactionDao(GNBankDatabase: GNBankDatabase) =
        GNBankDatabase.transactionDao()

    @Provides
    @Singleton
    fun provideRatesDao(GNBankDatabase: GNBankDatabase) =
        GNBankDatabase.rateDao()
}