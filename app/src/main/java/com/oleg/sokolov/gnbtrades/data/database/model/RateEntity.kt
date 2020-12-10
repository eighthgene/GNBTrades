package com.oleg.sokolov.gnbtrades.data.database.model

import androidx.room.Entity
import com.oleg.sokolov.gnbtrades.core.base.data.DomainMapper
import com.oleg.sokolov.gnbtrades.data.database.RATES_FROM_PRIMARY_KEY
import com.oleg.sokolov.gnbtrades.data.database.RATES_TABLE_NAME
import com.oleg.sokolov.gnbtrades.data.database.RATES_TO_PRIMARY_KEY
import com.oleg.sokolov.gnbtrades.domain.model.Rate


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
        rate = rate
    )
}