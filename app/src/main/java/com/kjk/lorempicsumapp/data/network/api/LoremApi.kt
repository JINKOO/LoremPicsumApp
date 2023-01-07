package com.kjk.lorempicsumapp.data.network.api

import com.kjk.lorempicsumapp.data.network.entity.LoremPictureRS
import retrofit2.http.GET

interface LoremApi {
    @GET("/v2/list")
    suspend fun getLoremPictureList(): List<LoremPictureRS>
}