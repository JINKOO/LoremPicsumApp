package com.kjk.lorempicsumapp.domain.usecase

import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import kotlinx.coroutines.flow.Flow

interface LoremPictureUseCase {
    fun getLoremPictureList(): Flow<List<LoremPicture>>
    fun getLoremPictureDetail(loremPictureId: String): Flow<LoremPicture>
}