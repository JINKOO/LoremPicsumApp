package com.kjk.lorempicsumapp.domain.repository

import com.kjk.lorempicsumapp.domain.entity.LoremPictureVO

interface LoremPictureRepository {
    suspend fun getLoremPicureList(): Result<List<LoremPictureVO>>
}