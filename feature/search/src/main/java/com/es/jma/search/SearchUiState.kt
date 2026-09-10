package com.es.jma.search

import com.es.jma.model.Breed

sealed interface SearchUiState {
    data object Initial : SearchUiState
    data class Success(
        val query: String = "",
        val isSearching: Boolean = false,
        val breeds: List<Breed> = emptyList(),
        val expandedBreedId: String? = null,
        val imagesByBreed: Map<String, List<String>> = emptyMap(),
        val isLoadingImage: Set<String> = emptySet()
    ) : SearchUiState
}