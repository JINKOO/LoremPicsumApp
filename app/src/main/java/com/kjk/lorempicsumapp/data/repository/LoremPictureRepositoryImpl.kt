package com.kjk.lorempicsumapp.data.repository

import com.kjk.lorempicsumapp.data.datasource.remote.LoremPictureRemoteSource
import com.kjk.lorempicsumapp.domain.entity.LoremPictureVO
import com.kjk.lorempicsumapp.domain.repository.LoremPictureRepository
import javax.inject.Inject

class LoremPictureRepositoryImpl @Inject constructor(
    private val loremPictureRemoteSource: LoremPictureRemoteSource
) : LoremPictureRepository {
    override suspend fun getLoremPicureList(): Result<List<LoremPictureVO>> = loremPictureRemoteSource.getLoremPictureList()
        .fold(
            onSuccess = {
                Result.success(
                    it.map { loremPicture ->
                        LoremPictureVO(
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