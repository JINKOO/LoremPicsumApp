package com.kjk.lorempicsumapp.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import com.kjk.lorempicsumapp.domain.usecase.LoremPictureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = false,
    val isFetchComplete: Boolean = false,
    val pictureList: List<LoremPicture> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loremPictureUseCase: LoremPictureUseCase
) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState(isLoading = false))
    val homeUiState = _homeUiState.asStateFlow()

    val pagingFlow = loremPictureUseCase.pagingFlow.cachedIn(viewModelScope)

//    fun fetchPictureList() {
//        _homeUiState.update {
//            it.copy(isLoading = true)
//        }
//        viewModelScope.launch {
//            Timber.d("fetchPictureList")
//            loremPictureUseCase.getLoremPictureList().collectLatest { pictureList ->
//                Timber.d("fetchPictureList :: pictureList :: ${pictureList.size}")
//                _homeUiState.update {
//                    it.copy(
//                        isLoading = false,
//                        isFetchComplete = true,
//                        pictureList = pictureList
//                    )
//                }
//            }
//        }
//    }
}