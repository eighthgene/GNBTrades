package com.oleg.sokolov.gnbtrades.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oleg.sokolov.gnbtrades.data.database.RATES_TABLE_NAME
import com.oleg.sokolov.gnbtrades.data.database.TRANSACTIONS_TABLE_NAME
import com.oleg.sokolov.gnbtrades.data.database.model.RateEntity
import com.oleg.sokolov.gnbtrades.data.database.model.TransactionEntity

@Dao
interface RatesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRate(rates: List<RateEntity>)

    @Query("SELECT * FROM $RATES_TABLE_NAME")
    suspend fun getRates(): List<RateEntity>
}