package com.kjk.lorempicsumapp.data.di

import android.content.Context
import androidx.room.Room
import com.kjk.lorempicsumapp.data.local.database.LoremPictureDao
import com.kjk.lorempicsumapp.data.local.database.LoremPictureDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideLoremPictureDatabase(
        @ApplicationContext context: Context
    ): LoremPictureDatabase {
        return Room.databaseBuilder(
            context,
            LoremPictureDatabase::class.java,
            "lorem_picture_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesLoremPictureDao(
        loremPictureDatabase: LoremPictureDatabase
    ): LoremPictureDao {
        return loremPictureDatabase.loremPictureDao()
    }
}