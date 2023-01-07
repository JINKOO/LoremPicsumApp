package com.kjk.lorempicsumapp.data.di

import com.kjk.lorempicsumapp.data.repository.LoremPictureRepositoryImpl
import com.kjk.lorempicsumapp.domain.repository.LoremPictureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLoremPictureRepository(
        loremPictureRepositoryImpl: LoremPictureRepositoryImpl
    ): LoremPictureRepository
}