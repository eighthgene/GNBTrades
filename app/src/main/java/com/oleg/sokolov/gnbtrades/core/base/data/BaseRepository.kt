package com.oleg.sokolov.gnbtrades.core.base.data

import com.oleg.sokolov.gnbtrades.core.base.domain.model.Failure
import com.oleg.sokolov.gnbtrades.core.base.domain.model.HttpError
import com.oleg.sokolov.gnbtrades.core.base.domain.model.Result
import com.oleg.sokolov.gnbtrades.core.base.domain.model.Success
import com.oleg.sokolov.gnbtrades.core.coroutine.CoroutineContextProvider
import com.oleg.sokolov.gnbtrades.core.network.Connectivity
import com.oleg.sokolov.gnbtrades.data.networking.DB_ENTRY_ERROR
import com.oleg.sokolov.gnbtrades.data.networking.GENERAL_NETWORK_ERROR
import kotlinx.coroutines.withContext

abstract class BaseRepository<T : Any, R : DomainMapper<T>> constructor(
    private val connectivity: Connectivity,
    val contextProvider: CoroutineContextProvider
) {


    /**
     * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun fetchData(
        apiDataProvider: suspend () -> Result<T>,
        dbDataProvider: suspend () -> R
    ): Result<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                apiDataProvider()
            }
        } else {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                if (dbResult != null) Success(dbResult.mapToDomainModel()) else Failure(
                    HttpError(
                        Throwable(DB_ENTRY_ERROR)
                    )
                )
            }
        }
    }

    /**
     * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun fetchData(
        dbDataProvider: suspend () -> R
    ): Result<T> {
        return withContext(contextProvider.io) {
            val dbResult = dbDataProvider()
            if (dbResult != null) Success(dbResult.mapToDomainModel()) else Failure(
                HttpError(
                    Throwable(DB_ENTRY_ERROR)
                )
            )
        }
    }


}