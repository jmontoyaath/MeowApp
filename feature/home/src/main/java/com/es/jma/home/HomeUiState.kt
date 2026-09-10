package com.es.jma.home

import androidx.paging.PagingData
import com.es.jma.model.CatInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(
        val data: Flow<PagingData<CatInfo>> = emptyFlow(),
    ) : HomeUiState
}

