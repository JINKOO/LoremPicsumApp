package com.kjk.lorempicsumapp.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kjk.lorempicsumapp.data.local.LoremPictureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LoremPictureDao {

    @Query("SELECT * FROM lorem_picture")
    fun getAllLoremPictures(): Flow<List<LoremPictureEntity>>

    @Query("SELECT * FROM lorem_picture WHERE id = :id")
    fun getLoremPicture(id: String): Flow<LoremPictureEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLoremPicture(loremPictureEntity: LoremPictureEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLoremPictures(pictureList: List<LoremPictureEntity>)

    @Update
    suspend fun updateLoremPicture(loremPictureEntity: LoremPictureEntity)

    @Delete
    suspend fun deleteLoremPicture(loremPictureEntity: LoremPictureEntity)
}