package com.oleg.sokolov.gnbtrades.ui.products.presentation


import com.oleg.sokolov.gnbtrades.data.common.coroutine.CoroutineContextProvider
import com.oleg.sokolov.gnbtrades.data.common.utils.Connectivity
import com.oleg.sokolov.gnbtrades.domain.interaction.products.GetProductListUseCase
import com.oleg.sokolov.gnbtrades.domain.model.onFailure
import com.oleg.sokolov.gnbtrades.domain.model.onSuccess
import com.oleg.sokolov.gnbtrades.ui.base.view.Error
import com.oleg.sokolov.gnbtrades.ui.base.view.NoInternetState
import com.oleg.sokolov.gnbtrades.ui.base.view.Success
import com.oleg.sokolov.gnbtrades.ui.base.viewmodel.BaseViewModel
import com.oleg.sokolov.gnbtrades.ui.products.model.ProductsAction
import com.oleg.sokolov.gnbtrades.ui.products.model.ProductsScreen
import com.oleg.sokolov.gnbtrades.ui.products.model.ProductsViewEffects
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    conn: Connectivity,
    contextProvider: CoroutineContextProvider
) : BaseViewModel<List<ProductsScreen>, ProductsAction, ProductsViewEffects>(
    conn,
    contextProvider
) {

    private val productsList = ArrayList<ProductsScreen>()

    override fun onAction(action: ProductsAction) {
        when (action) {
            is ProductsAction.OnViewStarted -> {
                if (productsList.isEmpty()) getProductsList()
            }
            is ProductsAction.OnItemClick -> _viewEffects.value =
                ProductsViewEffects.NavigateToDetails(productsList[action.position].name)
        }
    }

    private fun getProductsList() = executeUseCase {
        getProductListUseCase()
            .onSuccess {
                runOnUI {
                    if (!connectivity.hasNetworkAccess()) {
                        _viewState.value = NoInternetState()
                    }
                    productsList.clear()
                    productsList.addAll(it.map { ProductsScreen(it) })
                    _viewState.value = Success(productsList)
                }
            }
            .onFailure {
                runOnUI {
                    _viewState.value = Error(it.throwable)
                }
            }
    }
}