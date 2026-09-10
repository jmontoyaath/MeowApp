package com.es.jma.home

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.es.jma.domain.usecase.GetCatImagesSuspendedUseCase
import com.es.jma.domain.usecase.GetFavoriteIdsUseCase
import com.es.jma.domain.usecase.ValidateCatParam
import com.es.jma.domain.usecase.ValidateFavoriteUseCase
import com.es.jma.home.paging.CatPagingSource
import com.es.jma.model.CatInfo
import com.es.jma.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCatImagesUseCase: GetCatImagesSuspendedUseCase,
    private val validateFavoriteUseCase: ValidateFavoriteUseCase,
    private val getFavoriteIdsUseCase: GetFavoriteIdsUseCase,
) : BaseViewModel<HomeUiState, HomeAction>(HomeUiState.Loading) {

    private val pagingDataFlow: Flow<PagingData<CatInfo>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { CatPagingSource(getCatImagesUseCase, pageSize = PAGE_SIZE) }
    ).flow.cachedIn(viewModelScope)

    init {
        loadCats()
    }

    fun loadCats() {
        viewModelScope.launch {
            val catsWithFavorites = combine(
                pagingDataFlow,
                getFavoriteIdsUseCase(parameters = Unit)
            ) { pagingData, favoriteIds ->
                pagingData.map { cat -> cat.copy(isFavorite = cat.id in favoriteIds) }
            }

            updateState { HomeUiState.Success(data = catsWithFavorites) }
        }
    }

    fun onCatClicked(cat: CatInfo) {
        HomeAction.ShowDetailModal(
            catId = cat.id,
            urlImage = cat.url,
            breedId = cat.idBreed
        ).send()
    }

    fun onFavoriteClicked(cat: CatInfo, isFavorite: Boolean) {
        viewModelScope.launch {
            runCatching {
                validateFavoriteUseCase(ValidateCatParam(catInfo = cat, isFavorite = isFavorite))
            }.onFailure {
                HomeAction.ShowErrorAddFavorite.send()
            }
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}