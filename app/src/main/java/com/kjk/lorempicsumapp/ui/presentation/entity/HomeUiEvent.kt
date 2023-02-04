package com.kjk.lorempicsumapp.ui.presentation.entity

import com.kjk.lorempicsumapp.domain.entity.LoremPicture

data class HomeUiState(
    val isLoading: Boolean = false,
    val pictureList: List<LoremPicture> = emptyList()
)