package com.kjk.lorempicsumapp.data.datasource.remote

import com.kjk.lorempicsumapp.data.network.entity.LoremPictureApiModel

// TODO 질문 :: 회사에서 현재 진행 중인 프로젝트에서는 remoteSource를 사용하지 않고, repository에서 곧바로 Api호출
interface LoremPictureRemoteSource {
    suspend fun getLoremPictureList(): Result<List<LoremPictureApiModel>>
    suspend fun getLoremPictureDetail(loremPictureId: String): Result<LoremPictureApiModel>
}