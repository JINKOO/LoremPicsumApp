package com.kjk.lorempicsumapp.domain.repository

import com.kjk.lorempicsumapp.domain.entity.LoremPicture

interface LoremPictureRepository {
    suspend fun getLoremPictureList(): List<LoremPicture>
    suspend fun getLoremPictureDetail(loremPictureId: String): LoremPicture?
}