package com.kjk.lorempicsumapp.data.di

import com.kjk.lorempicsumapp.data.datasource.remote.LoremPictureRemoteSource
import com.kjk.lorempicsumapp.data.datasource.remote.LoremPictureRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindLoremPictureRemoteSource(
        loremPictureRemoteSourceImpl: LoremPictureRemoteSourceImpl
    ): LoremPictureRemoteSource
}