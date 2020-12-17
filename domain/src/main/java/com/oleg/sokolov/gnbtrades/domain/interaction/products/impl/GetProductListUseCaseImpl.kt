package com.oleg.sokolov.gnbtrades.domain.interaction.products.impl

import com.oleg.sokolov.gnbtrades.domain.interaction.products.GetProductListUseCase
import com.oleg.sokolov.gnbtrades.domain.model.Failure
import com.oleg.sokolov.gnbtrades.domain.model.Result
import com.oleg.sokolov.gnbtrades.domain.model.Success
import com.oleg.sokolov.gnbtrades.domain.repository.TransactionsRepository
import javax.inject.Inject

class GetProductListUseCaseImpl @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) : GetProductListUseCase {

    override suspend fun invoke(): Result<List<String>> {
        return when(val transactions = transactionsRepository.getTransactions()){
            is Success -> {
                return when(val productsNames = transactionsRepository.getProductsNames()){
                    is Success -> {
                        Success(productsNames.data.sorted())
                    }
                    is Failure -> Failure(productsNames.error)
                }
            }
            is Failure -> Failure(transactions.error)
        }
    }

}
