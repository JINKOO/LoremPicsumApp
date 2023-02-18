package com.kjk.lorempicsumapp.data.datasource.remote

import com.kjk.lorempicsumapp.data.network.entity.LoremPictureApiModel

interface LoremPictureRemoteSource {
    suspend fun getLoremPictureList(
        page: Int,
        pageSize: Int
    ): Result<List<LoremPictureApiModel>>

    suspend fun getLoremPictureDetail(loremPictureId: String): Result<LoremPictureApiModel>
}