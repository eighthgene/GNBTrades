package com.oleg.sokolov.gnbtrades.domain.base

import com.oleg.sokolov.gnbtrades.domain.model.Result

interface BaseUseCase<out R : Any> {
    suspend operator fun invoke(): Result<R>
}