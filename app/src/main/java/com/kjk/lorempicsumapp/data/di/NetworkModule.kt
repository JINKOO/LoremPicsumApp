package com.kjk.lorempicsumapp.data.di

import com.google.gson.Gson
import com.kjk.lorempicsumapp.data.network.api.LoremApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class LoremPictureApi

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class LoremPictureRetrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @LoremPictureRetrofit
    @Provides
    @Singleton
    fun provideLoremPictureService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://picsum.photos")
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