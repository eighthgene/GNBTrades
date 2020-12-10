package com.oleg.sokolov.gnbtrades.ui.transactions.model

data class TransactionsScreen(
    val name: String,
    val currency: String,
    val amount: String,
    val amountEur: String
)

data class TransactionsScreeData(
    val list: List<TransactionsScreen>,
    val total: String
)