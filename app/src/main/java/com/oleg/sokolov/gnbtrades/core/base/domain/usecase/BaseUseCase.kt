package com.oleg.sokolov.gnbtrades.core.base.domain.usecase

import  com.oleg.sokolov.gnbtrades.core.base.domain.model.Result

interface BaseUseCase<out R: Any> {
  suspend operator fun invoke(): Result<R>
}