package com.oleg.sokolov.gnbtrades.data.di

import com.oleg.sokolov.gnbtrades.data.repository.RatesRepositoryImpl
import com.oleg.sokolov.gnbtrades.data.repository.TransactionsRepositoryImpl
import com.oleg.sokolov.gnbtrades.domain.repository.RatesRepository
import com.oleg.sokolov.gnbtrades.domain.repository.TransactionsRepository
import org.koin.dsl.module


val repositoryModule = module {

    factory<TransactionsRepository> { TransactionsRepositoryImpl(GNBankApi = get(), transactionsDao = get()) }
    factory<RatesRepository> { RatesRepositoryImpl(GNBankApi = get(), ratesDao = get()) }

}