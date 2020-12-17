package com.oleg.sokolov.gnbtrades.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oleg.sokolov.gnbtrades.ui.products.presentation.ProductsViewModel
import com.oleg.sokolov.gnbtrades.ui.transactions.presentation.TransactionsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    internal abstract fun productsViewModel(viewModel: ProductsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionsViewModel::class)
    internal abstract fun transactionsViewModel(viewModel: TransactionsViewModel): ViewModel
}