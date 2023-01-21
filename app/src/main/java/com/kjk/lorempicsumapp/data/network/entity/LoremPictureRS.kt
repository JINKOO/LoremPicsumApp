package com.kjk.lorempicsumapp.data.network.entity

import com.google.gson.annotations.SerializedName

/**
 *  Data Layer에서 사용하는 Model
 */
data class LoremPictureRS(
    val id: String,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    @SerializedName("download_url")
    val downloadUrl: String
)