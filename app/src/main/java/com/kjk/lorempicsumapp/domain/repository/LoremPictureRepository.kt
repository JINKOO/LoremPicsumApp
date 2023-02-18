package com.kjk.lorempicsumapp.domain.repository

import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import kotlinx.coroutines.flow.Flow

interface LoremPictureRepository {
    suspend fun getLoremPictureListFromRemote(
        page: Int,
        pageSize: Int
    ): List<LoremPicture>

    fun getLoremPictureDetailFromLocal(loremPictureId: String): Flow<LoremPicture?>
    fun getLoremPictureListFromLocal(): Flow<List<LoremPicture>>
}