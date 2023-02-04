package com.kjk.lorempicsumapp.data.di

import com.kjk.lorempicsumapp.data.network.api.LoremApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LoremPictureApi

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LoremPictureRetrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://picsum.photos"
    }

    @LoremPictureRetrofit
    @Provides
    @Singleton
    fun provideLoremPictureService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @LoremPictureApi
    @Provides
    @Singleton
    fun provideLoremPictureApi(
        @LoremPictureRetrofit retrofit: Retrofit
    ): LoremApi {
        return retrofit.create(LoremApi::class.java)
    }
}