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

    override suspend fun getLoremPictureListFromRemote(
        page: Int,
        pageSize: Int
    ): List<LoremPicture> =
        loremPictureRemoteSource.getLoremPictureList(page, pageSize)
            .fold(
                onSuccess =
                {
                    // TODO 질문 :: network로 부터 받아온 정보를 room에 insert. 이방식이 맞는 지?
                    loremPictureLocalSource.insertAll(
                        it.map { loremPictureApiModel ->
                            LoremPictureEntity(loremPictureApiModel)
                        }
                    )
                    // TODO 질문 :: 이 부분은 필요 없는 것일까? Network로 부터 받아온 정보를 어짜피 room에 저장하고,
                    //  UI나, UseCase에서는 room에있는 data를 읽어오면 되니까??
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

    override fun getLoremPictureDetailFromLocal(loremPictureId: String): Flow<LoremPicture?> {
        return loremPictureLocalSource.getLoremPicture(loremPictureId).map { loremPictureEntity ->
            loremPictureEntity?.let {
                LoremPicture(it)
            }
        }
    }
}