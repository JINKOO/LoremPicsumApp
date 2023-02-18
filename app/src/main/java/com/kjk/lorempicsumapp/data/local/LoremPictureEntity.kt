package com.kjk.lorempicsumapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kjk.lorempicsumapp.data.network.entity.LoremPictureApiModel

@Entity(tableName = "lorem_picture")
data class LoremPictureEntity(
    @PrimaryKey
    val id: String,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    val downloadUrl: String
) {
    companion object {
        operator fun invoke(loremPictureApiModel: LoremPictureApiModel): LoremPictureEntity {
            return LoremPictureEntity(
                loremPictureApiModel.id,
                loremPictureApiModel.author,
                loremPictureApiModel.width,
                loremPictureApiModel.height,
                loremPictureApiModel.downloadUrl,
                loremPictureApiModel.url
            )
        }
    }
}