package com.oleg.sokolov.gnbtrades.data.networking.model

import com.oleg.sokolov.gnbtrades.data.database.model.TransactionEntity
import com.oleg.sokolov.gnbtrades.data.database.model.TransactionEntityList
import com.oleg.sokolov.gnbtrades.data.networking.base.RoomMapper

data class TransactionResponse(val transactionList: List<TransactionItemResponse>) :
    RoomMapper<TransactionEntityList> {
    override fun mapToRoomEntity(): TransactionEntityList {
        return TransactionEntityList(transactionList.map { it.mapToRoomEntity() })
    }
}

data class TransactionItemResponse(
    val sku: String?,
    val amount: String?,
    val currency: String?
) : RoomMapper<TransactionEntity> {
    override fun mapToRoomEntity() =
        TransactionEntity(
            sku ?: ERROR_PRODUCT_NAME,
            amount ?: "0",
            currency ?: ERROR_PRODUCT_CURRENCY
        )
}

const val ERROR_PRODUCT_NAME = "Unknown"
const val ERROR_PRODUCT_CURRENCY = "Unknown"