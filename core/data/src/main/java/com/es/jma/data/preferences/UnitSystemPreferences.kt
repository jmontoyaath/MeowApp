package com.es.jma.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.es.jma.model.UnitSystemEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UnitSystemPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val unitConfigKey = stringPreferencesKey("unit_system_config")

    val unitConfig: Flow<UnitSystemEnum> = dataStore.data.map { prefs ->
        UnitSystemEnum.valueOf(
            prefs[unitConfigKey] ?: UnitSystemEnum.METRICS.name
        )
    }

    suspend fun setUnitSystem(config: UnitSystemEnum) {
        dataStore.edit { it[unitConfigKey] = config.name }
    }
}