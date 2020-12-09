package com.oleg.sokolov.gnbtrades.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

import com.oleg.sokolov.gnbtrades.data.database.dao.TransactionsDao
import com.oleg.sokolov.gnbtrades.data.database.model.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class TransactionsDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionsDao

}