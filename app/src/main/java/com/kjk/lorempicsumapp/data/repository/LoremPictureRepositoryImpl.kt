package com.kjk.lorempicsumapp.data.repository

import com.kjk.lorempicsumapp.data.datasource.remote.LoremPictureRemoteSource
import com.kjk.lorempicsumapp.domain.entity.LoremPictureUiModel
import com.kjk.lorempicsumapp.domain.repository.LoremPictureRepository
import javax.inject.Inject

class LoremPictureRepositoryImpl @Inject constructor(
    private val loremPictureRemoteSource: LoremPictureRemoteSource
) : LoremPictureRepository {
    override suspend fun getLoremPicureList(): Result<List<LoremPictureUiModel>> = loremPictureRemoteSource.getLoremPictureList()
        .fold(
            onSuccess = {
                Result.success(
                    it.map { loremPicture ->
                        LoremPictureUiModel(
                            id = loremPicture.id,
                            author = loremPicture.author,
                            width = loremPicture.width,
                            height = loremPicture.height,
                            url = loremPicture.url,
                            downloadUrl = loremPicture.downloadUrl
                        )
                    }
                )
            },
            onFailure = {
                Result.failure(it)
            }
        )
}