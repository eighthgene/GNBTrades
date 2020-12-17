package com.oleg.sokolov.gnbtrades.domain.repository

import com.oleg.sokolov.gnbtrades.domain.model.Result
import com.oleg.sokolov.gnbtrades.domain.model.Transaction

interface TransactionsRepository {

    suspend fun getTransactions(): Result<List<Transaction>>

    suspend fun getTransactions(product : String): Result<List<Transaction>>

    suspend fun getProductsNames(): Result<List<String>>

}