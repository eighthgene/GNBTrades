package com.oleg.sokolov.gnbtrades.domain.interaction.products.impl

import com.oleg.sokolov.gnbtrades.core.base.domain.model.*
import com.oleg.sokolov.gnbtrades.domain.interaction.products.GetProductListUseCase
import com.oleg.sokolov.gnbtrades.domain.model.Transaction
import com.oleg.sokolov.gnbtrades.domain.repository.TransactionsRepository
import timber.log.Timber
import javax.inject.Inject

class GetProductListUseCaseImpl @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) : GetProductListUseCase {

    override suspend fun invoke(): Result<List<String>> {
        return when(val transactions = transactionsRepository.getTransactions()){
            is Success -> {
                return when(val productsNames = transactionsRepository.getProductsNames()){
                    is Success -> {
                        Success(productsNames.data)
                    }
                    is Failure -> Failure(productsNames.error)
                }
            }
            is Failure -> Failure(transactions.error)
        }
    }

}
