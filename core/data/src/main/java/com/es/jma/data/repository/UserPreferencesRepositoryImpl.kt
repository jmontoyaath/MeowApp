package com.es.jma.data.repository

import com.es.jma.data.preferences.ThemePreferences
import com.es.jma.data.preferences.UnitSystemPreferences
import com.es.jma.model.ThemeConfigEnum
import com.es.jma.model.UnitSystemEnum
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val themePreferences: ThemePreferences,
    private val unitSystemPreferences: UnitSystemPreferences
) : UserPreferencesRepository {
    override val themeConfig: Flow<ThemeConfigEnum> = themePreferences.themeConfig

    override suspend fun setThemeConfig(config: ThemeConfigEnum) {
        themePreferences.setThemeConfig(config)
    }

    override val unitSystemConfig: Flow<UnitSystemEnum> = unitSystemPreferences.unitConfig

    override suspend fun setUnitSystem(unitSystem: UnitSystemEnum) {
        unitSystemPreferences.setUnitSystem(unitSystem)
    }
}