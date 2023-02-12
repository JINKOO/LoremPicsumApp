package com.kjk.lorempicsumapp.domain.repository

import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import kotlinx.coroutines.flow.Flow

interface LoremPictureRepository {
    suspend fun getLoremPictureListFromRemote(): List<LoremPicture>
    suspend fun getLoremPictureDetail(loremPictureId: String): LoremPicture?
    fun getLoremPictureListFromLocal(): Flow<List<LoremPicture>>
}