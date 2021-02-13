package com.oleg.sokolov.gnbtrades.di

import com.oleg.sokolov.gnbtrades.ui.products.presentation.ProductsViewModel
import com.oleg.sokolov.gnbtrades.ui.products.view.ProductsAdapter
import com.oleg.sokolov.gnbtrades.ui.transactions.presentation.TransactionsViewModel
import com.oleg.sokolov.gnbtrades.ui.transactions.view.TransactionsAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { ProductsViewModel(getProductListUseCase = get()) }
    viewModel { TransactionsViewModel(getTransactionsUseCase = get(), getRatesUseCase = get()) }

    factory { ProductsAdapter() }
    factory { TransactionsAdapter() }
}