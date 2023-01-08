package com.kjk.lorempicsumapp.ui.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kjk.lorempicsumapp.domain.entity.LoremPictureUiModel
import com.kjk.lorempicsumapp.ui.presentation.home.LoremPictureItem

@Composable
fun LoremPictureImageWrapper(
    modifier: Modifier = Modifier,
    loremPicture: LoremPictureUiModel,
    onNavigateToDetail: (LoremPictureUiModel) -> Unit = {}
) {
    LoremPictureItem(
        loremPicture = loremPicture,
        navigateToDetail = onNavigateToDetail
    )
}