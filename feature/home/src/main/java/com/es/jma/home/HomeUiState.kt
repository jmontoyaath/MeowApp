package com.es.jma.home

import androidx.paging.PagingData
import com.es.jma.model.CatInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeUiState (
    val catsPaged: Flow<PagingData<CatInfo>> = emptyFlow(),
    val showError: Boolean = false,
    val loading: Boolean = false,
)

data class Cats(
    val catList: List<CatInfo>?
)