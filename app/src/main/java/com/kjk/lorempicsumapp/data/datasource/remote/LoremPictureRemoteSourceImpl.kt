package com.kjk.lorempicsumapp.data.datasource.remote

import com.kjk.lorempicsumapp.data.di.LoremPictureApi
import com.kjk.lorempicsumapp.data.network.api.LoremApi
import com.kjk.lorempicsumapp.data.network.entity.LoremPictureRS
import javax.inject.Inject

class LoremPictureRemoteSourceImpl @Inject constructor(
    @LoremPictureApi private val loremApi: LoremApi
) : LoremPictureRemoteSource {

    // TODO FLOW로 변경 한다.
    override suspend fun getLoremPictureList(): Result<List<LoremPictureRS>> = runCatching {
        loremApi.getLoremPictureList()
    }
}