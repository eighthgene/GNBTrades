package com.oleg.sokolov.gnbtrades.domain.api

import com.oleg.sokolov.gnbtrades.domain.interaction.products.GetProductListUseCase
import com.oleg.sokolov.gnbtrades.domain.interaction.rates.GetRatesUseCase
import com.oleg.sokolov.gnbtrades.domain.interaction.transactions.GetTransactionsUseCase

interface DomainApi {

    fun productUseCase(): GetProductListUseCase

    fun transactionsUseCase(): GetTransactionsUseCase

    fun ratesUseCase(): GetRatesUseCase

}