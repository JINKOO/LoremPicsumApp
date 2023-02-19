package com.kjk.lorempicsumapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kjk.lorempicsumapp.domain.entity.LoremPicture
import com.kjk.lorempicsumapp.domain.repository.LoremPictureRepository
import javax.inject.Inject

class LoremPagingSource @Inject constructor(
    private val loremPictureRepository: LoremPictureRepository
) : PagingSource<Int, LoremPicture>() {

    // TODO 질문 ::  getRefreshKey의 동작원리??
    override fun getRefreshKey(state: PagingState<Int, LoremPicture>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LoremPicture> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = loremPictureRepository.getLoremPictureListFromRemote(
                page = nextPageNumber,
                // TODO 질문 :: 왜 처음 laod될때는 사이즈가 *3 인가
                pageSize = params.loadSize
            )
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}