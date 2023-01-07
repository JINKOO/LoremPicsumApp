package com.kjk.lorempicsumapp.domain.entity

data class LoremPictureVO(
    val id: String,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    val downloadUrl: String
)