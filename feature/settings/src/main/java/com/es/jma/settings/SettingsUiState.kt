package com.es.jma.settings

import com.es.jma.model.ThemeConfigEnum
import com.es.jma.model.UnitSystemEnum

sealed interface SettingsUiState {
    data object Loading : SettingsUiState
    data class Success(
        val theme: ThemeConfigEnum,
        val unitSystem: UnitSystemEnum
    ) : SettingsUiState
    data object Error : SettingsUiState
}