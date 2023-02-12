package com.kjk.lorempicsumapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lorem_picture")
data class LoremPictureEntity(
    @PrimaryKey
    val id: String,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    val downloadUrl: String
) 