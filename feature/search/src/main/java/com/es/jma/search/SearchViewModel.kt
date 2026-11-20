package com.es.jma.search

import androidx.lifecycle.viewModelScope
import com.es.jma.designsystem.theme.MeowMotion.STANDARD
import com.es.jma.domain.usecase.GetImagesCatByBreedUseCase
import com.es.jma.domain.usecase.SearchCatBreedsUseCase
import com.es.jma.ui.BaseViewModel
import com.es.jma.utils.constants.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList
import kotlin.time.Duration.Companion.milliseconds

private const val LIMIT_SEARCH = 3

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    val searchCatBreedsUseCase: SearchCatBreedsUseCase,
    val getImagesCatByBreedUseCase: GetImagesCatByBreedUseCase
) : BaseViewModel<SearchUiState, SearchAction>(SearchUiState.Initial) {

    private val queryFlow = MutableStateFlow(value = EMPTY_STRING)
    private var successInfo: SearchUiState.Success = SearchUiState.Success()

    init {
        viewModelScope.launch {
            queryFlow
                .debounce(timeout = STANDARD.milliseconds)
                .distinctUntilChanged()
                .collectLatest { query -> performSearch(query) }
        }
    }

    fun onQueryChanged(newQuery: String) {
        updateSuccess { copy(query = newQuery) }
        queryFlow.value = newQuery
    }

    private suspend fun performSearch(query: String) {
        if (query.length < LIMIT_SEARCH) {
            updateSuccess { copy(breeds = emptyList()) }
            return
        }

        updateSuccess { copy(isSearching = true) }

        searchCatBreedsUseCase(query)
            .onSuccess { breeds ->
                updateSuccess { copy(breeds = breeds, isSearching = false) }
            }
            .onFailure { _ ->
                updateSuccess { copy(isSearching = false) }
            }
    }

    fun onBreedTapped(breedId: String) {
        val current = successInfo
        if (current.expandedBreedId == breedId) {
            updateSuccess { copy(expandedBreedId = null) }
            return
        }

        updateSuccess { copy(expandedBreedId = breedId) }
        if (current.imagesByBreed.containsKey(breedId)) return

        getImagesBreedSelected(breedId)
    }

    private fun getImagesBreedSelected(breedId: String) {
        viewModelScope.launch {
            updateSuccess { copy(isLoadingImage = successInfo.isLoadingImage + breedId) }

            getImagesCatByBreedUseCase(breedId)
                .onSuccess { images ->
                    val urls = images.map { it.url }
                    updateSuccess {
                        copy(
                            imagesByBreed = successInfo.imagesByBreed + (breedId to urls),
                            isLoadingImage = successInfo.isLoadingImage - breedId
                        )
                    }
                }
                .onFailure {
                    updateSuccess { copy(isLoadingImage = successInfo.isLoadingImage - breedId) }
                }
        }
    }

    private fun updateSuccess(updateBlock: SearchUiState.Success.() -> SearchUiState.Success) {
        state.update { state ->
            val currentSuccess = (state as? SearchUiState.Success) ?: successInfo
            updateBlock(currentSuccess).also { successInfo = it }
        }
    }
}