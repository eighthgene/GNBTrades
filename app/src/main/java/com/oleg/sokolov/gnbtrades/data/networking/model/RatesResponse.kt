package com.oleg.sokolov.gnbtrades.data.networking.model

import com.oleg.sokolov.gnbtrades.core.base.data.RoomMapper
import com.oleg.sokolov.gnbtrades.data.database.model.RateEntity
import com.oleg.sokolov.gnbtrades.data.database.model.RatesEntityList

data class RatesResponse(val rateList: List<RateItemResponse>) :
    RoomMapper<RatesEntityList> {
    override fun mapToRoomEntity(): RatesEntityList {
        return RatesEntityList(rateList.map { it.mapToRoomEntity() })
    }
}

data class RateItemResponse(
    val from: String?,
    val to: String?,
    val rate: String?
) : RoomMapper<RateEntity> {
    override fun mapToRoomEntity() =
        RateEntity(
            from ?: ERROR_RATE,
            to ?: ERROR_RATE,
            rate ?: ERROR_RATE
        )
}

const val ERROR_RATE = "Unknown"
