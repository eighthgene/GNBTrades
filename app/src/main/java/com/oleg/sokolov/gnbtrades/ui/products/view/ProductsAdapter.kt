package com.oleg.sokolov.gnbtrades.ui.products.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oleg.sokolov.gnbtrades.R
import com.oleg.sokolov.gnbtrades.ui.base.adapter.BaseAdapter
import com.oleg.sokolov.gnbtrades.ui.base.adapter.BaseViewHolder
import com.oleg.sokolov.gnbtrades.ui.products.model.ProductsScreen
import kotlinx.android.synthetic.main.product_item.view.*
import javax.inject.Inject

class ProductsAdapter @Inject constructor() : BaseAdapter<ProductsScreen>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ProductsScreen> {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        )
    }

    class ProductViewHolder(itemView: View) : BaseViewHolder<ProductsScreen>(itemView) {
        override fun bind(model: ProductsScreen) {
            itemView.product_name.text = model.name
        }

    }
}