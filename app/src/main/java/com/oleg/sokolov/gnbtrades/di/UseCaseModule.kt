package com.oleg.sokolov.gnbtrades.di

import com.oleg.sokolov.gnbtrades.domain.interaction.products.GetProductListUseCase
import com.oleg.sokolov.gnbtrades.domain.interaction.products.impl.GetProductListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class UseCaseModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun provideGetProductList(getProductListUseCaseImpl: GetProductListUseCaseImpl): GetProductListUseCase

}