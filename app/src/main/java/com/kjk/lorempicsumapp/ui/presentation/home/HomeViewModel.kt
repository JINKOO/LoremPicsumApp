package com.kjk.lorempicsumapp.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kjk.lorempicsumapp.domain.usecase.LoremPictureUseCase
import com.kjk.lorempicsumapp.ui.presentation.entity.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _homeUiState: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState(isLoading = true))
    val homeUiState = _homeUiState.asStateFlow()

    fun fetchPictureList() {
        viewModelScope.launch {
            Timber.d("fetchPictureList")
            loremPictureUseCase.getLoremPictureList().collectLatest { pictureList ->
                Timber.d("fetchPictureList :: pictureList :: ${pictureList.size}")
                _homeUiState.update {
                    it.copy(
                        isLoading = false,
                        pictureList = pictureList
                    )
                }
            }
        }
    }
}