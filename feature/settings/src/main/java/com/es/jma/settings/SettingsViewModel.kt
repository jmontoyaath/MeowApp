package com.es.jma.settings

import androidx.lifecycle.viewModelScope
import com.es.jma.data.repository.UserPreferencesRepository
import com.es.jma.domain.usecase.UpdateThemeAppUseCase
import com.es.jma.domain.usecase.UpdateUnitSystemAppUseCase
import com.es.jma.model.ThemeConfigEnum
import com.es.jma.model.UnitSystemEnum
import com.es.jma.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    val updateThemeAppUseCase: UpdateThemeAppUseCase,
    val updateUnitSystemAppUseCase: UpdateUnitSystemAppUseCase,
    userPreferencesRepository: UserPreferencesRepository
) : BaseViewModel<SettingsUiState, SettingsAction>(SettingsUiState.Loading) {

    init {
        viewModelScope.launch {
            combine(
                userPreferencesRepository.themeConfig,
                userPreferencesRepository.unitSystemConfig
            ) { themeConfig, unitSystem ->
                SettingsUiState.Success(
                    theme = themeConfig,
                    unitSystem = unitSystem
                )
            }.collect { newState ->
                updateState { newState }
            }
        }
    }

    fun updateThemeConfig(themeConfig: ThemeConfigEnum) {
        viewModelScope.launch { updateThemeAppUseCase.invoke(themeConfig) }
    }

    fun updateUnitSystem(unitSystem: UnitSystemEnum) {
        viewModelScope.launch { updateUnitSystemAppUseCase.invoke(unitSystem) }
    }
}