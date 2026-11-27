package com.es.jma.meowapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.es.jma.data.repository.UserPreferencesRepository
import com.es.jma.model.ThemeConfigEnum
import com.es.jma.model.UnitSystemEnum
import com.es.jma.utils.constants.FIVE_MILLIS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MeowViewModel @Inject constructor(
    userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val uiState: StateFlow<MainActivityUiState> = combine(
        userPreferencesRepository.themeConfig,
        userPreferencesRepository.unitSystemConfig
    ) { config, unit ->
        MainActivityUiState.Success(
            themeConfig = config,
            unitSystem = unit
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(FIVE_MILLIS),
    )
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(
        val themeConfig: ThemeConfigEnum,
        val unitSystem: UnitSystemEnum) : MainActivityUiState
}