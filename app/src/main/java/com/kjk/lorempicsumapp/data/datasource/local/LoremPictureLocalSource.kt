package com.kjk.lorempicsumapp.data.datasource.local

import com.kjk.lorempicsumapp.data.local.LoremPictureEntity
import kotlinx.coroutines.flow.Flow

// Local Data Source
interface LoremPictureLocalSource {

    suspend fun insertAll(pictureList: List<LoremPictureEntity>)

    fun getAllPictures(): Flow<List<LoremPictureEntity>>

    fun getLoremPicture(id: String): Flow<LoremPictureEntity?>
}