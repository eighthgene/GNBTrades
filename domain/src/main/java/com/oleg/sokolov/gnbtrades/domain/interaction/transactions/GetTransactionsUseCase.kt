package com.oleg.sokolov.gnbtrades.domain.interaction.transactions

import com.oleg.sokolov.gnbtrades.domain.base.BaseUseCaseWithParams
import com.oleg.sokolov.gnbtrades.domain.model.Result
import com.oleg.sokolov.gnbtrades.domain.model.Transaction

interface GetTransactionsUseCase : BaseUseCaseWithParams<String, List<Transaction>> {

    override suspend fun invoke(param: String): Result<List<Transaction>>

}