package com.oleg.sokolov.gnbtrades.data.networking.model

import com.squareup.moshi.FromJson

class RatesResponseAdapter {

    @FromJson
    fun fromJson(list: List<RateItemResponse>): RatesResponse {
        return RatesResponse(list)
    }

}