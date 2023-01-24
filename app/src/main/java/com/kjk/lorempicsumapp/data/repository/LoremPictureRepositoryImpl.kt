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
                        LoremPicture(
                            id = loremPictureApiModel.id,
                            author = loremPictureApiModel.author,
                            width = loremPictureApiModel.width,
                            height = loremPictureApiModel.height,
                            url = loremPictureApiModel.url,
                            downloadUrl = loremPictureApiModel.downloadUrl
                        )
                    }
                },
                onFailure = {
                    throw it
                }
            )

    override suspend fun getLoremPictureDetail(
        lorePictureId: String
    ): LoremPicture =
        loremPictureRemoteSource.getLoremPictureDetail(lorePictureId)
            .fold(
                onSuccess = { loremPictureRS ->
                    LoremPicture(
                        id = loremPictureRS.id,
                        author = loremPictureRS.author,
                        width = loremPictureRS.width,
                        height = loremPictureRS.height,
                        url = loremPictureRS.url,
                        downloadUrl = loremPictureRS.downloadUrl
                    )
                },
                onFailure = {
                    Timber.d("Failuer :: ${it}")
                    throw it
                }
            )
}