package com.es.jma.settings.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.es.jma.navigation.SettingsRoute
import com.es.jma.settings.SettingsScreen

fun EntryProviderScope<NavKey>.settingsEntry() {
    entry<SettingsRoute> {
        SettingsScreen()
    }
}