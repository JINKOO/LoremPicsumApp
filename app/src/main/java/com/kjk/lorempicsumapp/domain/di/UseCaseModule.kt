package com.kjk.lorempicsumapp.domain.di

import com.kjk.lorempicsumapp.domain.usecase.LoremPictureUseCase
import com.kjk.lorempicsumapp.domain.usecase.LoremPictureUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindLoremPictureUseCase(
        useCaseImpl: LoremPictureUseCaseImpl
    ): LoremPictureUseCase
}