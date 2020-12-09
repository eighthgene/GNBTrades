package com.oleg.sokolov.gnbtrades.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oleg.sokolov.gnbtrades.core.base.data.DomainMapper
import com.oleg.sokolov.gnbtrades.data.database.TRANSACTIONS_TABLE_NAME
import com.oleg.sokolov.gnbtrades.domain.model.Transaction
import java.math.BigDecimal

@Entity(tableName = TRANSACTIONS_TABLE_NAME)
data class TransactionEntity(
    @PrimaryKey
    val name: String,
    val amount: String,
    val currency: String
) : DomainMapper<Transaction> {
    override fun mapToDomainModel() = Transaction(
        name = name,
        amount = BigDecimal(amount),
        currency = currency
    )
}