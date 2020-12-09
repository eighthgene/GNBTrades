package com.oleg.sokolov.gnbtrades.domain.interaction.products.impl

import com.oleg.sokolov.gnbtrades.core.base.domain.model.*
import com.oleg.sokolov.gnbtrades.domain.interaction.products.GetProductListUseCase
import com.oleg.sokolov.gnbtrades.domain.model.Transaction
import com.oleg.sokolov.gnbtrades.domain.repository.TransactionsRepository
import javax.inject.Inject

class GetProductListUseCaseImpl @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) : GetProductListUseCase {

    override suspend fun invoke(): Result<List<String>> {
        return when(val transactions = transactionsRepository.getTransactions()){
            is Success -> Success(getProductNames(transactions = transactions.data))
            is Failure -> Failure(transactions.error)
        }
    }

    private fun getProductNames(transactions: List<Transaction>): List<String> {
        return transactions.distinctBy { it.name }.map { transaction -> transaction.name }
    }

}