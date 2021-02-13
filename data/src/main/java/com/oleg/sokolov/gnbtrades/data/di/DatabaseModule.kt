package com.oleg.sokolov.gnbtrades.data.di


import androidx.room.Room
import com.oleg.sokolov.gnbtrades.data.database.GNBankDatabase

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val TRANSACTIONS_DB = "transactions-database"

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), GNBankDatabase::class.java, TRANSACTIONS_DB)
            .fallbackToDestructiveMigration().build()
    }

    factory { get<GNBankDatabase>().rateDao() }
    factory { get<GNBankDatabase>().transactionDao() }
}