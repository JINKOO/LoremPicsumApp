package com.kjk.lorempicsumapp.data.datasource.remote

import com.kjk.lorempicsumapp.data.di.LoremPictureApi
import com.kjk.lorempicsumapp.data.network.api.LoremApi
import com.kjk.lorempicsumapp.data.network.entity.LoremPictureApiModel
import javax.inject.Inject

class LoremPictureRemoteSourceImpl @Inject constructor(
    @LoremPictureApi private val loremApi: LoremApi
) : LoremPictureRemoteSource {

    override suspend fun getLoremPictureList(
        page: Int,
        pageSize: Int
    ): Result<List<LoremPictureApiModel>> = runCatching {
        loremApi.getLoremPictureList(page, pageSize)
    }

    override suspend fun getLoremPictureDetail(
        loremPictureId: String
    ): Result<LoremPictureApiModel> = runCatching {
        loremApi.getLoremPictureDetail(loremPictureId)
    }
}