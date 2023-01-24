package com.kjk.lorempicsumapp.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
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
class LoremPictureViewModel @Inject constructor(
    private val loremPictureUseCase: LoremPictureUseCase
) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Idle)
    val homeUiState = _homeUiState.asStateFlow()

    private val _loremPictureList: MutableStateFlow<List<LoremPicture>> =
        MutableStateFlow(emptyList())
    val loremPictureList = _loremPictureList.asStateFlow()

    private val _loremPicture: MutableStateFlow<LoremPicture> = MutableStateFlow(LoremPicture())
    val loremPicture = _loremPicture.asStateFlow()

    private var _pictureId: String = ""

    fun onEvent(homeEvent: HomeEvent) {
        Timber.d("onEvent() : ${homeEvent}")
        when (homeEvent) {
            is HomeEvent.FetchPictures -> {
                fetchPictureList()
            }
        }
    }

    private fun fetchPictureList() {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d("fetchPictureList")
            loremPictureUseCase.getLoremPictureList().collectLatest { pictureList ->
                Timber.d("fetchPictureList :: pictureList :: ${pictureList.size}")
                _loremPictureList.update {
                    pictureList
                }
            }
        }
    }

    fun fetchPicture(loremPictureId: String) {
        viewModelScope.launch {
            Timber.d("fetchPicture :: ${Thread.currentThread().name}")
            loremPictureUseCase.getLoremPictureDetail(loremPictureId)
                .collectLatest { pictureDetail ->
                    Timber.d("PictureDetail :: ${pictureDetail}")
                    _loremPicture.update {
                        pictureDetail
                    }
                }
        }
    }

    fun setLoremPictureId(pictureId: String) {
        _pictureId = pictureId
        Timber.d("setPictureId :: ${_pictureId}")
    }

    fun getLoremPictureId(): String {
        Timber.d("getLoremPictureId ${_pictureId}")
        return _pictureId
    }

    fun setPreviousPictureId() {
        val currentPictureId = _pictureId.toInt().run {
            this.minus(1)
        }
        if (!isValidToShowPictureDetail(currentPictureId)) { // TODO index가 처음이라면 이전으로 넘어가기 없음.
            return
        }
        Timber.d("currentPictureId :: $currentPictureId")
        setLoremPictureId(currentPictureId.toString())
    }

    fun setNextPictureId() {
        val currentPictureId = _pictureId.toInt().run {
            this.plus(1)
        }
        if (!isValidToShowPictureDetail(currentPictureId)) {
            return
        }
        setLoremPictureId(currentPictureId.toString())
    }

    private fun isValidToShowPictureDetail(pictureId: Int): Boolean {
        return (pictureId >= 0 && pictureId <= loremPictureList.value.lastIndex)
    }
}