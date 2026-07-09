package com.es.jma.home

import androidx.lifecycle.viewModelScope
import com.es.jma.domain.usecase.GetCatImagesUseCase
import com.es.jma.model.CatInfo
import com.es.jma.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCatImagesUseCase: GetCatImagesUseCase
) : BaseViewModel<HomeUiState, HomeAction>(HomeUiState()) {

    init {
        loading()
        viewModelScope.launch {
            val result = getCatImagesUseCase.invoke(LIMIT)
            if (result.isSuccess) handleSuccess(result.getOrNull())
            else handleError()
        }
    }

    private fun loading(isLoading: Boolean = true) {
        _uiState.update {
            HomeUiState(
                loading = isLoading
            )
        }
    }

    private fun handleSuccess(catList: List<CatInfo>?) {
        _uiState.update {
            HomeUiState(
                loading = false,
                data = Cats(
                    catList = catList
                )
            )
        }
    }

    private fun handleError() {
        _uiState.update {
            HomeUiState(
                loading = false,
                showError = true
            )
        }
    }

    companion object {
        const val LIMIT = 10
    }
}