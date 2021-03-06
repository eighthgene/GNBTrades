package com.oleg.sokolov.gnbtrades.data.database.model

import com.oleg.sokolov.gnbtrades.core.base.data.DomainMapper
import com.oleg.sokolov.gnbtrades.domain.model.Rate

data class RatesEntityList(val rateList: List<RateEntity>) : DomainMapper<List<Rate>> {
    override fun mapToDomainModel() = rateList.map {
        it.mapToDomainModel()
    }
}
