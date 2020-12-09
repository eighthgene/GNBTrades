package com.oleg.sokolov.gnbtrades.core.base.domain.usecase

import  com.oleg.sokolov.gnbtrades.core.base.domain.model.Result

interface BaseUseCaseWithParams<T : Any, R : Any> {
    suspend operator fun invoke(param: T): Result<R>
}