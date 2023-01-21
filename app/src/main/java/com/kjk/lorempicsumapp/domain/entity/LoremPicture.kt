package com.kjk.lorempicsumapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *  Domain, UI Layer에서 사용하는 VO
 */
data class LoremPicture(
    var id: String = "",
    var author: String = "",
    var width: String = "",
    var height: String = "",
    var url: String = "",
    var downloadUrl: String = ""
)