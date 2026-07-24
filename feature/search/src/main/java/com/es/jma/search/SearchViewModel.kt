package com.es.jma.search

import androidx.lifecycle.viewModelScope
import com.es.jma.domain.usecase.GetImagesCatByBreedUseCase
import com.es.jma.domain.usecase.SearchCatBreedsUseCase
import com.es.jma.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList
import kotlin.time.Duration.Companion.milliseconds

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchCatBreedsUseCase: SearchCatBreedsUseCase,
    val getImagesCatByBreedUseCase: GetImagesCatByBreedUseCase
) : BaseViewModel<SearchUiState, SearchAction>(SearchUiState()) {

    private val queryFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            queryFlow
                .debounce(timeout = 300.milliseconds)
                .distinctUntilChanged()
                .collect { query -> performSearch(query) }
        }
    }

    fun onQueryChanged(newQuery: String) {
        updateState{ it.copy(query = newQuery) }
        queryFlow.value = newQuery
    }

    private suspend fun performSearch(query: String) {
        if (query.length < 3) {
            updateState { it.copy(breeds = emptyList(), isLoading = false, error = null) }
            return
        }

        updateState { it.copy(isLoading = true, error = null) }

        searchCatBreedsUseCase(query)
            .onSuccess { breeds ->
                updateState { it.copy(breeds = breeds, isLoading = false) }
            }
            .onFailure { throwable ->
                updateState { it.copy(isLoading = false, error = throwable.message) }
            }
    }

    fun onBreedTapped(breedId: String) {
        val current = _uiState.value
        if (current.expandedBreedId == breedId) {
            updateState { it.copy(expandedBreedId = null) }
            return
        }

        updateState { it.copy(expandedBreedId = breedId) }
        if (current.imagesByBreed.containsKey(breedId)) return

        getImagesBreedSelected(breedId)
    }

    private fun getImagesBreedSelected(breedId: String) {
        viewModelScope.launch {
            updateState { it.copy(isLoadingImage = it.isLoadingImage + breedId) }

            getImagesCatByBreedUseCase(breedId)
                .onSuccess { images ->
                    val urls = images.map { it.url }
                    updateState {
                        it.copy(
                            imagesByBreed = it.imagesByBreed + (breedId to urls),
                            isLoadingImage = it.isLoadingImage - breedId
                        )
                    }
                }
                .onFailure {
                    updateState { it.copy(isLoadingImage = it.isLoadingImage - breedId) }
                }
        }
    }
}