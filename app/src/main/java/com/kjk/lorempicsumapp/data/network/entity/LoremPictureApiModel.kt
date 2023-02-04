package com.kjk.lorempicsumapp.data.network.entity

import com.google.gson.annotations.SerializedName

data class LoremPictureApiModel(
    val id: String,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    @SerializedName("download_url")
    val downloadUrl: String
)