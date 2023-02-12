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

data class DetailPictureUiState(
    val isLoading: Boolean = false,
    val prevLoremPicture: LoremPicture? = null,
    val currLoremPicture: LoremPicture? = null,
    val nextLoremPicture: LoremPicture? = null,
    val errorMessage: String = ""
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loremPictureUseCase: LoremPictureUseCase
) : ViewModel() {

    private val pictureId: String = checkNotNull(savedStateHandle["pictureId"])

    private val _detailUiState = MutableStateFlow(DetailPictureUiState())
    val detailUiState = _detailUiState.asStateFlow()

    init {
        Timber.d("pictureId:: $pictureId")
        fetchLoremPicture(pictureId)
    }

    fun fetchLoremPicture(loremPictureId: String) {
        _detailUiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            try {
                loremPictureUseCase.getLoremPictureDetail(loremPictureId)
                    .collectLatest { loremPicture ->
                        Timber.d("fetchLoremPicture :: ${loremPicture}")
                        _detailUiState.update {
                            it.copy(
                                isLoading = false,
                                prevLoremPicture = loremPicture[0],
                                currLoremPicture = loremPicture[1],
                                nextLoremPicture = loremPicture[2]
                            )
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
}