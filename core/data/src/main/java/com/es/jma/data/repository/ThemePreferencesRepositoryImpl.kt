package com.es.jma.data.repository

import com.es.jma.data.preferences.ThemePreferences
import com.es.jma.model.ThemeConfigEnum
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemePreferencesRepositoryImpl @Inject constructor(
    private val preferences: ThemePreferences
) : ThemePreferencesRepository {
    override val themeConfig: Flow<ThemeConfigEnum> = preferences.themeConfig

    override suspend fun setThemeConfig(config: ThemeConfigEnum) {
        preferences.setThemeConfig(config)
    }
}