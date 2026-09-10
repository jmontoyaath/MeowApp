package com.es.jma.favorite

import com.es.jma.model.CatInfo

sealed interface FavoriteUiState {
    data object Loading : FavoriteUiState
    data class Success (
        val data: List<CatInfo>? = null,
    ) : FavoriteUiState
    data object Error : FavoriteUiState
}
