package com.kjk.lorempicsumapp.domain.usecase

import androidx.paging.PagingData
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import kotlinx.coroutines.flow.Flow

interface LoremPictureUseCase {
    val pagingFlow: Flow<PagingData<LoremPicture>>
    fun getLoremPictureList(): Flow<List<LoremPicture>>
    fun getLoremPictureDetail(loremPictureId: String): Flow<LoremPicture?>
}