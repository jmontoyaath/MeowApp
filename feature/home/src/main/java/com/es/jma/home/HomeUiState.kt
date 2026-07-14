package com.es.jma.home

import com.es.jma.model.CatInfo

data class HomeUiState (
    val data: Cats? = null,
    val showError: Boolean = false,
    val loading: Boolean = false
)

data class Cats(
    val catList: List<CatInfo>?
)