package com.kjk.lorempicsumapp.data.network.api

import com.kjk.lorempicsumapp.data.network.entity.LoremPictureRS
import retrofit2.http.GET
import retrofit2.http.Path

interface LoremApi {
    /**
     *  전체 리스트를 가져오는 API
     */
    @GET("/v2/list")
    suspend fun getLoremPictureList(): List<LoremPictureRS>

    /**
     *  id값을 기준으로 하나의 Picture 정보를 가져오는 API
     */
    @GET("/id/{id}/info")
    suspend fun getLoremPictureDetail(
        @Path("id") id: String
    ): LoremPictureRS
}