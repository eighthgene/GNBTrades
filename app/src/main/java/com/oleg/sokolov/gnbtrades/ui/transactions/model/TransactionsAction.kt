package com.oleg.sokolov.gnbtrades.ui.transactions.model

sealed class TransactionsAction {
    class OnViewLoaded(val product: String): TransactionsAction()
}