package com.kjk.lorempicsumapp.domain.usecase

import com.kjk.lorempicsumapp.domain.entity.LoremPictureVO
import com.kjk.lorempicsumapp.domain.repository.LoremPictureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoremPictureUseCaseImpl @Inject constructor(
    private val loremPictureRepository: LoremPictureRepository
) : LoremPictureUseCase {

    override suspend fun getLoremPictureList(): Flow<Result<List<LoremPictureVO>>> = flow {
        emit(loremPictureRepository.getLoremPicureList())
    }
}