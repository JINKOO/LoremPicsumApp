package com.kjk.lorempicsumapp.data.datasource.local

import com.kjk.lorempicsumapp.data.local.LoremPictureEntity
import com.kjk.lorempicsumapp.data.local.database.LoremPictureDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoremPictureLocalSourceImpl @Inject constructor(
    private val loremPictureDao: LoremPictureDao
) : LoremPictureLocalSource {

    override suspend fun insertAll(pictureList: List<LoremPictureEntity>) {
        loremPictureDao.insertAllLoremPictures(pictureList)
    }

    override fun getAllPictures(): Flow<List<LoremPictureEntity>> =
        loremPictureDao.getAllLoremPictures()

    override fun getLoremPicture(id: String): Flow<LoremPictureEntity?> =
        loremPictureDao.getLoremPicture(id)
}