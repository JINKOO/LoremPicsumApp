package com.kjk.lorempicsumapp.ui.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import com.kjk.lorempicsumapp.domain.usecase.LoremPictureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class PictureDetailUiState(
    val loremPicture: LoremPicture
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loremPictureUseCase: LoremPictureUseCase
) : ViewModel() {

    // TODO naviagtion으로 argument 전달하는 로직이 필요함.

    private fun fetchLoremPicture(loremPictureId: String) {
        viewModelScope.launch {
            loremPictureUseCase.getLoremPictureDetail(loremPictureId)
                .collectLatest { loremPicture ->
                    Timber.d("fetchLoremPicture :: ${loremPicture}")
//                    _detailUiState.update {
//
//                    }
                }
        }
    }
}