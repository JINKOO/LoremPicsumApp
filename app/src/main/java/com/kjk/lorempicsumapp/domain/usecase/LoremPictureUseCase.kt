package com.kjk.lorempicsumapp.domain.usecase

import com.kjk.lorempicsumapp.domain.entity.LoremPictureUiModel
import kotlinx.coroutines.flow.Flow

interface LoremPictureUseCase {
    suspend fun getLoremPictureList(): Flow<Result<List<LoremPictureUiModel>>>
}