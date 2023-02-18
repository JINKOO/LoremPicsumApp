package com.kjk.lorempicsumapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kjk.lorempicsumapp.data.local.LoremPictureEntity

@Database(entities = [LoremPictureEntity::class], version = 1)
abstract class LoremPictureDatabase : RoomDatabase() {

    abstract fun loremPictureDao(): LoremPictureDao
}