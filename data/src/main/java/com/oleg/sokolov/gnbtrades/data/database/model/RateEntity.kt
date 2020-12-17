package com.oleg.sokolov.gnbtrades.data.database.model

import androidx.room.Entity
import com.oleg.sokolov.gnbtrades.data.database.RATES_FROM_PRIMARY_KEY
import com.oleg.sokolov.gnbtrades.data.database.RATES_TABLE_NAME
import com.oleg.sokolov.gnbtrades.data.database.RATES_TO_PRIMARY_KEY
import com.oleg.sokolov.gnbtrades.data.networking.base.DomainMapper
import com.oleg.sokolov.gnbtrades.domain.model.Rate
import java.math.BigDecimal


@Entity(
    tableName = RATES_TABLE_NAME,
    primaryKeys = [RATES_FROM_PRIMARY_KEY, RATES_TO_PRIMARY_KEY]
)
data class RateEntity(
    val from: String,
    val to: String,
    val rate: String
) : DomainMapper<Rate> {
    override fun mapToDomainModel() = Rate(
        from = from,
        to = to,
        rate = BigDecimal(rate)
    )
}