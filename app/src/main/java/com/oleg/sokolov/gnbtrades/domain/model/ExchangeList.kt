package com.oleg.sokolov.gnbtrades.domain.model

import java.math.BigDecimal


data class ExchangeListTo(val list: List<Transaction>, val to: String)

data class ExchangeListFrom(val list: List<TransactionsExchanged>, val amount: BigDecimal)

