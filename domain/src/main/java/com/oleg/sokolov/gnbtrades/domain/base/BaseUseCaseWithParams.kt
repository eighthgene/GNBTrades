package com.oleg.sokolov.gnbtrades.domain.base

import com.oleg.sokolov.gnbtrades.domain.model.Result


interface BaseUseCaseWithParams<T : Any, R : Any> {
    suspend operator fun invoke(param: T): Result<R>
}