package com.kjk.lorempicsumapp.ui.presentation.home

/**
 *  ViewModel에서 Ui로 전달하는 UiState
 */
sealed interface HomeUiState {
    object Idle: HomeUiState
    object FetchPicturesComplete : HomeUiState
}

/**
 *  UI에서 ViewModel로 전달하는 Event
 */
sealed interface HomeEvent {
    object FetchPictures: HomeEvent
}