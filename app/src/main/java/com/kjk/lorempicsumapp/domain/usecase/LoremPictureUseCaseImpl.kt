package com.kjk.lorempicsumapp.domain.usecase

import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import com.kjk.lorempicsumapp.domain.repository.LoremPictureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoremPictureUseCaseImpl @Inject constructor(
    private val loremPictureRepository: LoremPictureRepository
) : LoremPictureUseCase {

    override fun getLoremPictureList(): Flow<List<LoremPicture>> = flow {
        emit(loremPictureRepository.getLoremPictureList())
    }

    override fun getLoremPictureDetail(
        loremPictureId: String
    ): Flow<LoremPicture> = flow {
        // TODO 질문 :: null 처리는 usecase에서 해야하나, 아니면, repository에서 해야하나?
        loremPictureRepository.getLoremPictureDetail(loremPictureId)?.let { emit(it) }
    }
}