package com.es.jma.home

import com.es.jma.model.CatInfo

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val cats: List<CatInfo>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}