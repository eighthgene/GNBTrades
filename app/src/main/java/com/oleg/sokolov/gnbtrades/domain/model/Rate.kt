package com.oleg.sokolov.gnbtrades.domain.model

import java.math.BigDecimal

data class Rate(val from: String, val to: String, val rate: BigDecimal)