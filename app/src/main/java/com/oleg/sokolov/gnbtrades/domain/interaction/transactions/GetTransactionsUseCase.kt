package com.oleg.sokolov.gnbtrades.domain.interaction.transactions

import com.oleg.sokolov.gnbtrades.core.base.domain.model.Result
import com.oleg.sokolov.gnbtrades.core.base.domain.usecase.BaseUseCase
import com.oleg.sokolov.gnbtrades.core.base.domain.usecase.BaseUseCaseWithParams
import com.oleg.sokolov.gnbtrades.domain.model.Transaction

interface GetTransactionsUseCase : BaseUseCaseWithParams<String, List<Transaction>> {

    override suspend fun invoke(param: String): Result<List<Transaction>>

}