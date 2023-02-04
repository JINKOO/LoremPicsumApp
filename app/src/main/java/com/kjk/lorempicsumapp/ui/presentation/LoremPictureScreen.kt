package com.kjk.lorempicsumapp.ui.presentation

import androidx.annotation.StringRes
import com.kjk.lorempicsumapp.R

enum class LoremPictureScreen(
    @StringRes val title: Int,
) {
    Home(title = R.string.home),
    Detail(title = R.string.detail)
}