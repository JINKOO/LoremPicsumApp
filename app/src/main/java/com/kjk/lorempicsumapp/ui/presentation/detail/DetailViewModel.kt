package com.kjk.lorempicsumapp.ui.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import com.kjk.lorempicsumapp.domain.usecase.LoremPictureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

// Detail Screen -> ViewModelEvent
sealed class DetailPictureViewModelEvent {
    data class RefreshCurrentPicture(
        val pictureId: String
    ) : DetailPictureViewModelEvent()
}

data class DetailPictureUiState(
    val isLoading: Boolean = false,
    val prevLoremPicture: LoremPicture = LoremPicture(),
    val currLoremPicture: LoremPicture = LoremPicture(),
    val nextLoremPicture: LoremPicture = LoremPicture(),
    val errorMessage: String = ""
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loremPictureUseCase: LoremPictureUseCase
) : ViewModel() {

    private val pictureId: String = savedStateHandle.get<String>("pictureId") ?: ""

    private val _detailUiState = MutableStateFlow(DetailPictureUiState())
    val detailUiState = _detailUiState.asStateFlow()

    init {
        Timber.d("pictureId:: $pictureId")
        fetchCurrentLoremPicture(pictureId)
        fetchPreviousLoremPicture(pictureId.toInt().minus(1).toString())
        fetchNextLoremPicture(pictureId.toInt().plus(1).toString())
    }

    fun event(event: DetailPictureViewModelEvent) {
        when (event) {
            is DetailPictureViewModelEvent.RefreshCurrentPicture -> {
                // TODO 예외 처리 필요 (첫번째 및 마지막 일때)
                fetchCurrentLoremPicture(event.pictureId)
                fetchPreviousLoremPicture(event.pictureId.toInt().minus(1).toString())
                fetchNextLoremPicture(event.pictureId.toInt().plus(1).toString())
            }
        }
    }

    private fun fetchCurrentLoremPicture(loremPictureId: String) {
        _detailUiState.update {
            it.copy(isLoading = true)
        }
        // TODO 뒤로가기 선택 할 때도, 해당 로직이 타는 이유, Recomposition?? 아래 catch 문을 탐
        viewModelScope.launch {
            try {
                Timber.d("fetchCurrentLoremPicture")
                loremPictureUseCase.getLoremPictureDetail(loremPictureId)
                    .collectLatest { loremPicture ->
                        // TODO Null Check를 반드시 해야하나?
                        loremPicture?.let { picture ->
                            Timber.d("fetchLoremPicture :: ${loremPicture}")
                            _detailUiState.update {
                                it.copy(currLoremPicture = picture)
                            }
                        } ?: run {
                            _detailUiState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = "Not Exist"
                                )
                            }
                        }
                    }
            } catch (e: Exception) {
                Timber.w("ERROR :: ${e.message}")
                _detailUiState.update {
                    it.copy(isLoading = false, errorMessage = "error")
                }
            }
        }
    }

    private fun fetchPreviousLoremPicture(loremPictureId: String) {
        viewModelScope.launch {
            try {
                loremPictureUseCase.getLoremPictureDetail(loremPictureId = loremPictureId)
                    .collectLatest { loremPicture ->
                        loremPicture?.let {
                            _detailUiState.update {
                                it.copy(
                                    prevLoremPicture = loremPicture
                                )
                            }
                        }
                    }
            } catch (e: Exception) {
                Timber.w("ERROR :: ${e.message}")
                _detailUiState.update {
                    it.copy(
                        errorMessage = "Error While fetch prev Picture"
                    )
                }
            }
        }
    }

    private fun fetchNextLoremPicture(loremPictureId: String) {
        viewModelScope.launch {
            try {
                loremPictureUseCase.getLoremPictureDetail(loremPictureId = loremPictureId)
                    .collectLatest { loremPicture ->
                        loremPicture?.let {
                            _detailUiState.update {
                                it.copy(
                                    nextLoremPicture = loremPicture
                                )
                            }
                        }
                    }
            } catch (e: Exception) {
                Timber.w("ERROR :: ${e.message}")
                _detailUiState.update {
                    it.copy(
                        errorMessage = "Error While fetch prev Picture"
                    )
                }
            }
        }
    }
}