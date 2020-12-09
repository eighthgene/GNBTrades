package com.oleg.sokolov.gnbtrades.ui.products.presentation

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.oleg.sokolov.gnbtrades.core.base.domain.model.onFailure
import com.oleg.sokolov.gnbtrades.core.base.domain.model.onSuccess
import com.oleg.sokolov.gnbtrades.core.base.presentation.viewmodel.BaseViewModel
import com.oleg.sokolov.gnbtrades.core.coroutine.CoroutineContextProvider
import com.oleg.sokolov.gnbtrades.core.network.Connectivity
import com.oleg.sokolov.gnbtrades.domain.interaction.products.GetProductListUseCase
import com.oleg.sokolov.gnbtrades.ui.products.model.ProductsAction
import com.oleg.sokolov.gnbtrades.ui.products.model.ProductsScreen
import com.oleg.sokolov.gnbtrades.ui.products.model.ProductsViewEffects

class ProductsViewModel @ViewModelInject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    conn: Connectivity,
    contextProvider: CoroutineContextProvider
): BaseViewModel<ProductsScreen, ProductsAction, ProductsViewEffects>(conn, contextProvider) {

    override fun onAction(action: ProductsAction) {
        when(action){
            ProductsAction.OnStart -> getProductsList()
        }
    }

    private fun getProductsList() = executeUseCase {
        getProductListUseCase()
            .onSuccess {  }
            .onFailure {  }
    }
}