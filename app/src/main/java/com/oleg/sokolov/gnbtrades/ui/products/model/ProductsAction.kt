package com.oleg.sokolov.gnbtrades.ui.products.model

sealed class ProductsAction {
    class OnItemClick(val position: Int) : ProductsAction()
    object OnViewStarted : ProductsAction()
}