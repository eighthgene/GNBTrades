package com.oleg.sokolov.gnbtrades.ui.products.model

sealed class ProductsViewEffects {
    class NavigateToDetails(val product: String) : ProductsViewEffects()
}