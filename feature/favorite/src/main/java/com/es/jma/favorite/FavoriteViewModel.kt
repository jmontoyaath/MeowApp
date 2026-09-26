package com.es.jma.favorite

import androidx.lifecycle.viewModelScope
import com.es.jma.domain.usecase.GetFavoriteCatsUseCase
import com.es.jma.domain.usecase.ValidateCatParam
import com.es.jma.domain.usecase.ValidateFavoriteUseCase
import com.es.jma.model.CatInfo
import com.es.jma.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteCatsUseCase: GetFavoriteCatsUseCase,
    private val validateFavoriteUseCase: ValidateFavoriteUseCase,
) : BaseViewModel<FavoriteUiState, FavoriteAction>(FavoriteUiState.Loading) {

    init {
        getFavorites()
    }

    fun getFavorites() {
        updateState { FavoriteUiState.Loading }
        viewModelScope.launch {
            getFavoriteCatsUseCase(Unit)
                .onEach { cats ->
                    updateState {
                        FavoriteUiState.Success(
                            data = cats
                        )
                    }
                }
                .catch {
                    updateState { FavoriteUiState.Error }
                }
                .collect()
        }
    }

    fun onDeleteFavorite(cat: CatInfo) {
        viewModelScope.launch {
            val result = validateFavoriteUseCase(ValidateCatParam(catInfo = cat))
            if (result.isFailure) FavoriteAction.ErrorDeletingFavorite.send()
        }
    }

    fun onCatClicked(cat: CatInfo) {
        FavoriteAction.ShowDetailModal(
            catId = cat.id,
            urlImage = cat.url,
            breedId = cat.idBreed
        ).send()
    }
}