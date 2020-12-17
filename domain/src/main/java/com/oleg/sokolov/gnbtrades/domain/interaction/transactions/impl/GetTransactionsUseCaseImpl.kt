package com.oleg.sokolov.gnbtrades.domain.interaction.transactions.impl

import com.oleg.sokolov.gnbtrades.domain.exceptions.EmptyParamException
import com.oleg.sokolov.gnbtrades.domain.interaction.transactions.GetTransactionsUseCase
import com.oleg.sokolov.gnbtrades.domain.model.BusinessError
import com.oleg.sokolov.gnbtrades.domain.model.Failure
import com.oleg.sokolov.gnbtrades.domain.model.Result
import com.oleg.sokolov.gnbtrades.domain.model.Transaction
import com.oleg.sokolov.gnbtrades.domain.repository.TransactionsRepository
import javax.inject.Inject

class GetTransactionsUseCaseImpl @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) : GetTransactionsUseCase {

    override suspend fun invoke(param: String): Result<List<Transaction>> {
        return if (param.isNotEmpty()) {
            transactionsRepository.getTransactions(param)
        } else {
            Failure(BusinessError(EmptyParamException()))
        }
    }
}