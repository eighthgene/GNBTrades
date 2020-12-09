package com.oleg.sokolov.gnbtrades.domain.interaction.products

import com.oleg.sokolov.gnbtrades.core.base.domain.model.Result
import com.oleg.sokolov.gnbtrades.core.base.domain.usecase.BaseUseCase

interface GetProductListUseCase : BaseUseCase<List<String>> {

    override suspend fun invoke(): Result<List<String>>
}