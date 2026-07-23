package com.es.jma.detail

import androidx.lifecycle.viewModelScope
import com.es.jma.domain.usecase.GetCatBreedDetailUseCase
import com.es.jma.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val getCatBreedDetailUseCase: GetCatBreedDetailUseCase,
) : BaseViewModel<DetailUiState, DetailAction>(DetailUiState.Loading) {

    fun getCatDetail(idBreed: String) {
        viewModelScope.launch {
            updateState { DetailUiState.Loading }
            val result = getCatBreedDetailUseCase.invoke(idBreed)
            if (result.isSuccess) {
                updateState {
                    DetailUiState.Success(
                        data = result.getOrNull()
                    )
                }
            } else updateState { DetailUiState.Error }
        }
    }
}