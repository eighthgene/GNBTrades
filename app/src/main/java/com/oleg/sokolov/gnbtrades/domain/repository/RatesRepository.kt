package com.oleg.sokolov.gnbtrades.domain.repository

import com.oleg.sokolov.gnbtrades.core.base.domain.model.Result
import com.oleg.sokolov.gnbtrades.domain.model.Rate

interface RatesRepository {

    suspend fun getRates(): Result<List<Rate>>

}