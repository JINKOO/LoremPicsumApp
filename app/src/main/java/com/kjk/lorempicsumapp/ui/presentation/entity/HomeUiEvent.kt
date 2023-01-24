package com.kjk.lorempicsumapp.ui.presentation.home

import com.kjk.lorempicsumapp.domain.entity.LoremPicture

/**
 *  ViewModel에서 Ui로 전달하는 UiState
 */
sealed interface HomeUiState {
    object Idle: HomeUiState
    data class FetchPicturesComplete(
        val pictureList: List<LoremPicture>
    ): HomeUiState
}

/**
 *  UI에서 ViewModel로 전달하는 Event
 */
sealed interface HomeEvent {
    object FetchPictures : HomeEvent
}

sealed interface DetailUiState {
    object Idle: DetailUiState
}

sealed interface DetailEvent {
    data class FetchPicture(
        val loremPictureId: String
    ): DetailEvent
}