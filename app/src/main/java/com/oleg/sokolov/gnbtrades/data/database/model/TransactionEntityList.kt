package com.oleg.sokolov.gnbtrades.data.database.model

import com.oleg.sokolov.gnbtrades.core.base.data.DomainMapper
import com.oleg.sokolov.gnbtrades.domain.model.Transaction


data class TransactionEntityList(val transactionList: List<TransactionEntity>) :
    DomainMapper<List<Transaction>> {
    override fun mapToDomainModel() = transactionList.map { it.mapToDomainModel() }
}
