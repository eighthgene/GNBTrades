package com.oleg.sokolov.gnbtrades.data

import com.oleg.sokolov.gnbtrades.data.networking.model.RatesResponse
import com.oleg.sokolov.gnbtrades.data.networking.model.TransactionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface GNBankApi {

    @Headers(
        "Accept: application/json",
        "Content-type:application/json"
    )
    @GET("transactions")
    suspend fun getTransactions(): Response<TransactionResponse>

    @Headers(
        "Accept: application/json",
        "Content-type:application/json"
    )

    @GET("rates")
    suspend fun getRates(): Response<RatesResponse>

}