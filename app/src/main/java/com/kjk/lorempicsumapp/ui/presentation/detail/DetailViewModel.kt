package com.kjk.lorempicsumapp.ui.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kjk.lorempicsumapp.domain.entity.LoremPictureVO
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val detailData = savedStateHandle.get<LoremPictureVO>(PICTURE_DETAIL_DATA)

    init {
        Timber.d("init{} :: ${detailData}")
    }

    companion object {
        const val PICTURE_DETAIL_DATA = "detailData"
    }
}