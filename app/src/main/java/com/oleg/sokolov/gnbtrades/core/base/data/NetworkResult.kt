package com.oleg.sokolov.gnbtrades.core.base.data

import com.oleg.sokolov.gnbtrades.core.base.domain.model.Success
import com.oleg.sokolov.gnbtrades.core.base.domain.model.Failure
import com.oleg.sokolov.gnbtrades.core.base.domain.model.HttpError
import com.oleg.sokolov.gnbtrades.core.base.domain.model.Result
import com.oleg.sokolov.gnbtrades.data.networking.DB_ENTRY_ERROR
import com.oleg.sokolov.gnbtrades.data.networking.GENERAL_NETWORK_ERROR
import retrofit2.Response
import java.io.IOException

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (HttpError) -> Unit) {
    if (!isSuccessful) errorBody()?.run { action(HttpError(Throwable(message()), code())) }
}

/**
 * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
 */

inline fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<T>.getData(
    cacheAction: (R) -> Unit,
    fetchFromCacheAction: () -> R
): Result<U> {
    try {
        onSuccess {
            val databaseEntity = it.mapToRoomEntity()
            cacheAction(databaseEntity)
            return Success(databaseEntity.mapToDomainModel())
        }
        onFailure {
            val cachedModel = fetchFromCacheAction()
            if (cachedModel != null) Success(cachedModel.mapToDomainModel()) else Failure(
                HttpError(
                    Throwable(DB_ENTRY_ERROR)
                )
            )
        }
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}
