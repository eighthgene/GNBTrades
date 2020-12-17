package com.oleg.sokolov.gnbtrades.di

import android.content.Context
import com.oleg.sokolov.gnbtrades.data.di.DatabaseModule
import com.oleg.sokolov.gnbtrades.data.di.NetworkingModule
import com.oleg.sokolov.gnbtrades.data.di.RepositoryModule
import com.oleg.sokolov.gnbtrades.domain.di.UseCaseModule
import com.oleg.sokolov.gnbtrades.ui.products.view.ProductsFragment
import com.oleg.sokolov.gnbtrades.ui.transactions.view.TransactionsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        NetworkingModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun crete(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: TransactionsFragment)
    fun inject(fragment: ProductsFragment)

}