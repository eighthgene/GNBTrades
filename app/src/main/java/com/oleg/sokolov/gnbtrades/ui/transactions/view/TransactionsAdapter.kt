package com.oleg.sokolov.gnbtrades.ui.transactions.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oleg.sokolov.gnbtrades.R
import com.oleg.sokolov.gnbtrades.core.base.presentation.adapter.BaseAdapter
import com.oleg.sokolov.gnbtrades.core.base.presentation.adapter.BaseViewHolder
import com.oleg.sokolov.gnbtrades.ui.transactions.model.TransactionsScreen
import kotlinx.android.synthetic.main.transaction_item.view.*
import javax.inject.Inject

class TransactionsAdapter @Inject constructor(): BaseAdapter<TransactionsScreen>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TransactionsScreen> {
        return TransactionViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        )
    }

    class TransactionViewHolder(itemView: View) : BaseViewHolder<TransactionsScreen>(itemView){
        override fun bind(model: TransactionsScreen) {
            itemView.transaction_product_name.text = model.name
            itemView.transaction_currency.text = model.currency
            itemView.transaction_amount.text = model.amount
            itemView.transaction_amount_eur.text = model.amountEur
        }

    }

}