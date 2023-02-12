package com.kjk.lorempicsumapp.data.datasource.local

import com.kjk.lorempicsumapp.data.local.LoremPictureEntity

// Local Data Source
interface LoremPictureLocalSource {

    suspend fun insertAll(pictureList: List<LoremPictureEntity>)
}