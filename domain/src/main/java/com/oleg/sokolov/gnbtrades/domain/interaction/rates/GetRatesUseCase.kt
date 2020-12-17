package com.oleg.sokolov.gnbtrades.domain.interaction.rates

import com.oleg.sokolov.gnbtrades.domain.base.BaseUseCaseWithParams
import com.oleg.sokolov.gnbtrades.domain.model.ExchangeListFrom
import com.oleg.sokolov.gnbtrades.domain.model.ExchangeListTo
import com.oleg.sokolov.gnbtrades.domain.model.Result

interface GetRatesUseCase : BaseUseCaseWithParams<ExchangeListTo, ExchangeListFrom> {

    override suspend fun invoke(param: ExchangeListTo): Result<ExchangeListFrom>

}