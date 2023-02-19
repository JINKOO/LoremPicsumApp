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

    // TODO 질문 :: pagingFlow는 공식문서에서는 ViewModel에서 사용하라고 권장하는데,
    // usecase를 사용하는 경우에는 아래와 같이 처리하는 것이 맞는지
    override val pagingFlow = Pager(
        pagingSourceFactory = { LoremPagingSource(loremPictureRepository) },
        config = PagingConfig(pageSize = 30)
    ).flow

    override fun getLoremPictureList(): Flow<List<LoremPicture>> =
        loremPictureRepository.getLoremPictureListFromLocal()

    // 상세화면에 대한 정보는 API 콜을 하는 것이 아니라, local db에서 가져오도록 한다.
    override fun getLoremPictureDetail(
        loremPictureId: String
    ): Flow<LoremPicture?> =
        loremPictureRepository.getLoremPictureDetailFromLocal(loremPictureId = loremPictureId)
}