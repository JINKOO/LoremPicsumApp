package com.kjk.lorempicsumapp.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kjk.lorempicsumapp.data.datasource.remote.LoremPictureRemoteSource
import com.kjk.lorempicsumapp.data.network.entity.LoremPictureRS
import com.kjk.lorempicsumapp.domain.entity.LoremPictureVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loremPictureRemoteSource: LoremPictureRemoteSource
) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Idle)
    val homeUiState = _homeUiState.asStateFlow()

    var loremPictureList: List<LoremPictureVO> = emptyList()

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
            // TODO fetch pictures
            Timber.d("response1")
            loremPictureRemoteSource.getLoremPictureList()
                .onSuccess { pictureList->
                    Timber.d("onSuccess :: ${pictureList.size}")
                    loremPictureList = pictureList.map { picture ->
                        LoremPictureVO(
                            id = picture.id,
                            url = picture.url,
                            author = picture.author,
                            height = picture.height,
                            width = picture.width,
                            downloadUrl = picture.downloadUrl
                        )
                    }
                    _homeUiState.update {
                        HomeUiState.FetchPicturesComplete
                    }
                }
                .onFailure {
                    Timber.d("onFetchPicture Fail :: ${it.message}")
                }
            Timber.d("response after")
        }
    }
}