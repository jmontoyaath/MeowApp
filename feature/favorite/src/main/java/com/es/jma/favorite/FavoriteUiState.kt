package com.es.jma.favorite

import com.es.jma.model.CatInfo

data class FavoriteUiState (
    val cats: List<CatInfo>? = null,
    val showError: Boolean = false,
    val loading: Boolean = false
)