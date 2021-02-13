package com.oleg.sokolov.gnbtrades.domain.di

import com.oleg.sokolov.gnbtrades.domain.interaction.products.GetProductListUseCase
import com.oleg.sokolov.gnbtrades.domain.interaction.products.impl.GetProductListUseCaseImpl
import com.oleg.sokolov.gnbtrades.domain.interaction.rates.GetRatesUseCase
import com.oleg.sokolov.gnbtrades.domain.interaction.rates.impl.GetRatesUseCaseImpl
import com.oleg.sokolov.gnbtrades.domain.interaction.transactions.GetTransactionsUseCase
import com.oleg.sokolov.gnbtrades.domain.interaction.transactions.impl.GetTransactionsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetProductListUseCase> {GetProductListUseCaseImpl(transactionsRepository = get())}
    factory<GetTransactionsUseCase> {GetTransactionsUseCaseImpl(transactionsRepository = get())}
    factory<GetRatesUseCase> {GetRatesUseCaseImpl(ratesRepository = get())}
}