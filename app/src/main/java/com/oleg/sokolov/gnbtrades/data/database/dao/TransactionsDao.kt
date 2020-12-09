package com.oleg.sokolov.gnbtrades.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oleg.sokolov.gnbtrades.data.database.TRANSACTIONS_TABLE_NAME
import com.oleg.sokolov.gnbtrades.data.database.model.TransactionEntity

@Dao
interface TransactionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTransaction(transactions: List<TransactionEntity>)

    @Query("SELECT * FROM $TRANSACTIONS_TABLE_NAME")
    suspend fun getTransactions(): List<TransactionEntity>

}