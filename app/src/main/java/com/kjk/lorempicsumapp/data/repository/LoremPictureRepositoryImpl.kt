package com.kjk.lorempicsumapp.data.repository

import com.kjk.lorempicsumapp.data.datasource.remote.LoremPictureRemoteSource
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import com.kjk.lorempicsumapp.domain.repository.LoremPictureRepository
import timber.log.Timber
import javax.inject.Inject

class LoremPictureRepositoryImpl @Inject constructor(
    private val loremPictureRemoteSource: LoremPictureRemoteSource
) : LoremPictureRepository {

    override suspend fun getLoremPictureList(): List<LoremPicture> =
        loremPictureRemoteSource.getLoremPictureList()
            .fold(
                onSuccess = {
                    it.map { loremPictureApiModel ->
                        LoremPicture(loremPictureApiModel)
                    }
                },
                onFailure = {
                    Timber.w("ERROR :: ${it.message}")
                    emptyList()
                }
            )

    override suspend fun getLoremPictureDetail(
        loremPictureId: String
    ): LoremPicture? {
        return loremPictureRemoteSource.getLoremPictureDetail(loremPictureId)
            .fold(
                onSuccess = { loremPictureApiModel ->
                    LoremPicture(loremPictureApiModel)
                },
                onFailure = {
                    Timber.w("ERROR :: ${it.message}")
                    null
                }
            )
    }
}