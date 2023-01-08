package com.kjk.lorempicsumapp.domain.repository

import com.kjk.lorempicsumapp.domain.entity.LoremPictureUiModel

interface LoremPictureRepository {
    suspend fun getLoremPicureList(): Result<List<LoremPictureUiModel>>
}