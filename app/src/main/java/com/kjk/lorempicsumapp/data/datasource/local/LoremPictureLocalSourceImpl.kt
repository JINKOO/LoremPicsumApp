package com.kjk.lorempicsumapp.data.datasource.local

import com.kjk.lorempicsumapp.data.local.LoremPictureEntity
import com.kjk.lorempicsumapp.data.local.database.LoremPictureDao
import timber.log.Timber
import javax.inject.Inject

class LoremPictureLocalSourceImpl @Inject constructor(
    private val loremPictureDao: LoremPictureDao
) : LoremPictureLocalSource {

    init {
        Timber.d("LoremPictureLocalDataSoruce")
    }

    override suspend fun insertAll(pictureList: List<LoremPictureEntity>) {
        loremPictureDao.insertAllLoremPictures(pictureList)
    }
}