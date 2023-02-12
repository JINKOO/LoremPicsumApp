package com.kjk.lorempicsumapp.data.repository

import com.kjk.lorempicsumapp.data.datasource.local.LoremPictureLocalSource
import com.kjk.lorempicsumapp.data.datasource.remote.LoremPictureRemoteSource
import com.kjk.lorempicsumapp.data.local.LoremPictureEntity
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import com.kjk.lorempicsumapp.domain.repository.LoremPictureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class LoremPictureRepositoryImpl @Inject constructor(
    private val loremPictureRemoteSource: LoremPictureRemoteSource,
    private val loremPictureLocalSource: LoremPictureLocalSource
) : LoremPictureRepository {

    override suspend fun getLoremPictureListFromRemote(): List<LoremPicture> =
        loremPictureRemoteSource.getLoremPictureList()
            .fold(
                onSuccess =
                {
                    loremPictureLocalSource.insertAll(
                        it.map { loremPictureApiModel ->
                            LoremPictureEntity(
                                id = loremPictureApiModel.id,
                                author = loremPictureApiModel.author,
                                width = loremPictureApiModel.width,
                                height = loremPictureApiModel.height,
                                url = loremPictureApiModel.url,
                                downloadUrl = loremPictureApiModel.downloadUrl
                            )
                        }
                    )
                    it.map { loremPictureApiModel ->
                        LoremPicture(loremPictureApiModel)
                    }
                },
                onFailure =
                {
                    Timber.w("ERROR :: ${it.message}")
                    emptyList()
                }
            )

    override fun getLoremPictureListFromLocal(): Flow<List<LoremPicture>> {
        return loremPictureLocalSource.getAllPictures().map {
            it.map { LoremPictureEntity ->
                LoremPicture(LoremPictureEntity)
            }
        }
    }

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