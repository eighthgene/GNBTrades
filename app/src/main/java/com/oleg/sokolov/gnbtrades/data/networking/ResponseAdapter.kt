package com.oleg.sokolov.gnbtrades.data.networking

import com.oleg.sokolov.gnbtrades.data.networking.model.TransactionItemResponse
import com.oleg.sokolov.gnbtrades.data.networking.model.TransactionResponse
import com.squareup.moshi.FromJson

class ResponseAdapter {

    @FromJson
    fun fromJson(list: List<TransactionItemResponse>): TransactionResponse {
        return TransactionResponse(list)
    }

}