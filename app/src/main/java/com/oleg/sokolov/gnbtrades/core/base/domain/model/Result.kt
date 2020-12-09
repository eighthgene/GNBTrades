package com.oleg.sokolov.gnbtrades.core.base.domain.model

sealed class Result<out T : Any>
data class Success<out T : Any>(val data: T) : Result<T>()
data class Failure(val error: Error) : Result<Nothing>()

abstract class Error(val throwable: Throwable, val errorCode: Int = 0)
class HttpError(t: Throwable, code: Int = 0) : Error(t, code)
class BusinessError(t: Throwable, code: Int = 0) : Error(t, code)

inline fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Success) action(data)
    return this
}

inline fun <T : Any> Result<T>.onFailure(action: (Error) -> Unit) {
    if (this is Failure) action(error)
}