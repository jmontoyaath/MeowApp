package com.es.jma.detail

import com.es.jma.model.Breed

sealed interface DetailUiState {
    data object Loading : DetailUiState
    data class Success (
        val data: Breed? = null,
    ) : DetailUiState
    data object Error : DetailUiState
}
