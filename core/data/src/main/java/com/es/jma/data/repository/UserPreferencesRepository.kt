package com.es.jma.data.repository

import com.es.jma.model.ThemeConfigEnum
import com.es.jma.model.UnitSystemEnum
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val themeConfig: Flow<ThemeConfigEnum>
    suspend fun setThemeConfig(config: ThemeConfigEnum)
    val unitSystemConfig: Flow<UnitSystemEnum>
    suspend fun setUnitSystem(unitSystem: UnitSystemEnum)
}