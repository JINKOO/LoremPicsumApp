package com.kjk.lorempicsumapp.domain.entity

import com.kjk.lorempicsumapp.data.local.LoremPictureEntity
import com.kjk.lorempicsumapp.data.network.entity.LoremPictureApiModel

/**
 *  Domain, UI Layer에서 사용하는 VO
 */
data class LoremPicture(
    var id: String = "",
    var author: String = "",
    var width: String = "",
    var height: String = "",
    var url: String = "",
    var downloadUrl: String = ""
) {
    companion object {
        // TODO 3번 Domain Layer에서 사용할 Entity에 대한 Mapper 생성
        // TODO 질문 :: data class API Model이나, Domain Model에서 nullable로 선언해야하나?
        operator fun invoke(loremPictureResponse: LoremPictureApiModel): LoremPicture {
            return LoremPicture(
                loremPictureResponse.id,
                loremPictureResponse.author,
                loremPictureResponse.width,
                loremPictureResponse.height,
                loremPictureResponse.url,
                loremPictureResponse.downloadUrl
            )
        }

        operator fun invoke(loremPictureEntity: LoremPictureEntity): LoremPicture {
            return LoremPicture(
                loremPictureEntity.id,
                loremPictureEntity.author,
                loremPictureEntity.width,
                loremPictureEntity.height,
                loremPictureEntity.url,
                loremPictureEntity.downloadUrl
            )
        }
    }
}