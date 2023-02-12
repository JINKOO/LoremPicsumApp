package com.kjk.lorempicsumapp.data.network.entity

import com.squareup.moshi.Json

// TODO 4번 Moshi 적용
data class LoremPictureApiModel(
    val id: String,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    @Json(name = "download_url")
    val downloadUrl: String
)