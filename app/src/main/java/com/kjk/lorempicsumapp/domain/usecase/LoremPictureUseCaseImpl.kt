package com.kjk.lorempicsumapp.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.kjk.lorempicsumapp.data.repository.LoremPagingSource
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import com.kjk.lorempicsumapp.domain.repository.LoremPictureRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoremPictureUseCaseImpl @Inject constructor(
    private val loremPictureRepository: LoremPictureRepository
) : LoremPictureUseCase {

    override val pagingFlow = Pager(
        pagingSourceFactory = { LoremPagingSource(loremPictureRepository) },
        config = PagingConfig(pageSize = 30)
    ).flow

    override fun getLoremPictureList(): Flow<List<LoremPicture>> =
        loremPictureRepository.getLoremPictureListFromLocal()

    // TODO 상세화면에 대한 정보는 API 콜을 하는 것이 아니라, local db에서 가져오도록 한다.
    override fun getLoremPictureDetail(
        loremPictureId: String
    ): Flow<LoremPicture?> =
        loremPictureRepository.getLoremPictureDetailFromLocal(loremPictureId = loremPictureId)
}