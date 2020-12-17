package com.oleg.sokolov.gnbtrades.domain.repository

import com.oleg.sokolov.gnbtrades.domain.model.Rate
import com.oleg.sokolov.gnbtrades.domain.model.Result

interface RatesRepository {

    suspend fun getRates(): Result<List<Rate>>

}