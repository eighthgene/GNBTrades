package com.oleg.sokolov.gnbtrades.domain.model

import java.math.BigDecimal


data class Transaction(
    val name: String,
    val amount: BigDecimal,
    val currency: String
)