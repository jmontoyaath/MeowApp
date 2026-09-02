package com.es.jma.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.es.jma.model.ThemeConfigEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemePreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val themeConfigKey = stringPreferencesKey("theme_config")

    val themeConfig: Flow<ThemeConfigEnum> = dataStore.data.map { prefs ->
        ThemeConfigEnum.valueOf(
            prefs[themeConfigKey] ?: ThemeConfigEnum.LIGHT.name
        )
    }

    suspend fun setThemeConfig(config: ThemeConfigEnum) {
        dataStore.edit { it[themeConfigKey] = config.name }
    }
}