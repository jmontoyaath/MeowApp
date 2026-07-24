package com.es.jma.search

import com.es.jma.model.Breed

data class SearchUiState (
    val query: String = "",
    val breeds: List<Breed> = emptyList(),
    val expandedBreedId: String? = null,
    val imagesByBreed: Map<String, List<String>> = emptyMap(),
    val isLoading: Boolean = false,
    val isLoadingImage: Set<String> = emptySet(),
    val error: String? = null
)