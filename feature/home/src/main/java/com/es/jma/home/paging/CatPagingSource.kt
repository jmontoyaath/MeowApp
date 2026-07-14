package com.es.jma.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.es.jma.domain.usecase.GetCatImagesParams
import com.es.jma.domain.usecase.GetCatImagesUseCase
import com.es.jma.model.CatInfo

class CatPagingSource (
    private val getCatImagesUseCase: GetCatImagesUseCase,
    private val pageSize: Int
) : PagingSource<Int, CatInfo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatInfo> {
        val page = params.key ?: 0

        val result = getCatImagesUseCase.invoke(
            GetCatImagesParams(limit = pageSize, page = page)
        )

        return result.fold(
            onSuccess = { cats ->
                LoadResult.Page(
                    data = cats,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (cats.isEmpty()) null else page + 1
                )
            },
            onFailure = { error ->
                LoadResult.Error(error)
            }
        )
    }

    override fun getRefreshKey(state: PagingState<Int, CatInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}