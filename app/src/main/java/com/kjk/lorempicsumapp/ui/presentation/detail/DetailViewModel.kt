package com.kjk.lorempicsumapp.ui.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kjk.lorempicsumapp.domain.usecase.LoremPictureUseCase
import com.kjk.lorempicsumapp.ui.presentation.home.DetailEvent
import com.kjk.lorempicsumapp.ui.presentation.home.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loremPictureUseCase: LoremPictureUseCase
) : ViewModel() {

    private val _detailUiState: MutableStateFlow<DetailUiState> =
        MutableStateFlow<DetailUiState>(DetailUiState.Idle)
    val detailUiState = _detailUiState.asStateFlow()

    init {
        Timber.d("${savedStateHandle.get<String>("pictureId")}")
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.FetchPicture -> {
                fetchLoremPicture(event.loremPictureId)
            }
        }
    }

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