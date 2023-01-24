package com.kjk.lorempicsumapp.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kjk.lorempicsumapp.domain.entity.LoremPictureUiModel
import com.kjk.lorempicsumapp.domain.usecase.LoremPictureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loremPictureUseCase: LoremPictureUseCase
) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Idle)
    val homeUiState = _homeUiState.asStateFlow()

    var loremPictureList: List<LoremPictureUiModel> = emptyList()

    fun onEvent(homeEvent: HomeEvent) {
        Timber.d("onEvent() : ${homeEvent}")
        when(homeEvent) {
            is HomeEvent.FetchPictures -> {
                fetchPicture()
            }
        }
    }

    private fun fetchPicture() {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d("response1")
            loremPictureUseCase.getLoremPictureList().collectLatest { result ->
                result.onSuccess {
                    loremPictureList = it
                    _homeUiState.update {
                        HomeUiState.FetchPicturesComplete
                    }
                }.onFailure {
                    Timber.w("onFailure :: ${it.message}")
                }
            }
        }
    }
}