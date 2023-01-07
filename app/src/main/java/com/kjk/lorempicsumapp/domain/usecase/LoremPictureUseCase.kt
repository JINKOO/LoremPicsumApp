package com.kjk.lorempicsumapp.domain.usecase

import com.kjk.lorempicsumapp.domain.entity.LoremPictureVO
import kotlinx.coroutines.flow.Flow

interface LoremPictureUseCase {
    suspend fun getLoremPictureList(): Flow<Result<List<LoremPictureVO>>>
}