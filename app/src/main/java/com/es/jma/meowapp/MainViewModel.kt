package com.es.jma.meowapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.es.jma.data.repository.ThemePreferencesRepository
import com.es.jma.model.ThemeConfigEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MeowViewModel @Inject constructor(
    themePreferencesRepository: ThemePreferencesRepository
) : ViewModel() {

    val uiState: StateFlow<MainActivityUiState> = themePreferencesRepository.themeConfig
        .map { config -> MainActivityUiState.Success(config) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainActivityUiState.Loading
        )
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val themeConfig: ThemeConfigEnum) : MainActivityUiState
}