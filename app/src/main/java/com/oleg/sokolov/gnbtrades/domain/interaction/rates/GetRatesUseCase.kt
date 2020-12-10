package com.oleg.sokolov.gnbtrades.domain.interaction.rates

import com.oleg.sokolov.gnbtrades.core.base.domain.model.Result
import com.oleg.sokolov.gnbtrades.core.base.domain.usecase.BaseUseCaseWithParams
import com.oleg.sokolov.gnbtrades.domain.model.ExchangeListFrom
import com.oleg.sokolov.gnbtrades.domain.model.ExchangeListTo
import java.math.BigDecimal

interface GetRatesUseCase : BaseUseCaseWithParams<ExchangeListTo, ExchangeListFrom> {

    override suspend fun invoke(param: ExchangeListTo): Result<ExchangeListFrom>

}