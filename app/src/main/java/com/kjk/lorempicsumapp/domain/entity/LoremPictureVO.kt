package com.kjk.lorempicsumapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoremPictureVO(
    var id: String = "",
    var author: String = "",
    var width: String = "",
    var height: String = "",
    var url: String = "",
    var downloadUrl: String = ""
): Parcelable