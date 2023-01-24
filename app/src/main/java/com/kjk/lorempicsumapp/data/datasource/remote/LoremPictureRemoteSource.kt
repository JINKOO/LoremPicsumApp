package com.kjk.lorempicsumapp.data.datasource.remote

import com.kjk.lorempicsumapp.data.network.entity.LoremPictureRS

interface LoremPictureRemoteSource {
    suspend fun getLoremPictureList(): Result<List<LoremPictureRS>>
    suspend fun getLoremPictureDetail(loremPictureId: String): Result<LoremPictureRS>
}