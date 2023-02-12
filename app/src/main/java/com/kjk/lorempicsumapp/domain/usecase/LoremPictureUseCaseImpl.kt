package com.kjk.lorempicsumapp.domain.usecase

import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import com.kjk.lorempicsumapp.domain.repository.LoremPictureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoremPictureUseCaseImpl @Inject constructor(
    private val loremPictureRepository: LoremPictureRepository
) : LoremPictureUseCase {

    override fun getLoremPictureList(): Flow<List<LoremPicture>> = loremPictureRepository.getLoremPictureListFromLocal()

    override fun getLoremPictureDetail(
        loremPictureId: String
    ): Flow<List<LoremPicture?>> = flow {
        // TODO 질문 :: null 처리는 usecase에서 해야하나, 아니면, repository에서 해야하나?
        val currentId = loremPictureId.toInt()
        val currentLoremPicture =
            loremPictureRepository.getLoremPictureDetail(loremPictureId)
        val prevLoremPicture =
            loremPictureRepository.getLoremPictureDetail(currentId.minus(1).toString())
        val nextLoremPicture =
            loremPictureRepository.getLoremPictureDetail(currentId.plus(1).toString())
        emit(
            listOf(
                prevLoremPicture,
                currentLoremPicture,
                nextLoremPicture
            )
        )
    }
}