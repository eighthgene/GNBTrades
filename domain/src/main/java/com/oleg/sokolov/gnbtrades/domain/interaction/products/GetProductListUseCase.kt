package com.oleg.sokolov.gnbtrades.domain.interaction.products


import com.oleg.sokolov.gnbtrades.domain.base.BaseUseCase
import com.oleg.sokolov.gnbtrades.domain.model.Result

interface GetProductListUseCase : BaseUseCase<List<String>> {

    override suspend fun invoke(): Result<List<String>>
}