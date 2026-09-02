package com.es.jma.data.repository

import com.es.jma.model.ThemeConfigEnum
import kotlinx.coroutines.flow.Flow

interface ThemePreferencesRepository {
    val themeConfig: Flow<ThemeConfigEnum>
    suspend fun setThemeConfig(config: ThemeConfigEnum)
}