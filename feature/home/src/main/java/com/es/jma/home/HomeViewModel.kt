package com.es.jma.home

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.es.jma.domain.usecase.GetCatImagesUseCase
import com.es.jma.home.paging.CatPagingSource
import com.es.jma.model.CatInfo
import com.es.jma.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCatImagesUseCase: GetCatImagesUseCase,
) : BaseViewModel<HomeUiState, HomeAction>(HomeUiState()) {

    val catsPaged: Flow<PagingData<CatInfo>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { CatPagingSource(getCatImagesUseCase, PAGE_SIZE) }
    ).flow.cachedIn(viewModelScope)


    companion object {
        const val PAGE_SIZE = 10
    }
}