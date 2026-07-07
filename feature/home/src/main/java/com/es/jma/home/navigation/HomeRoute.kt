package com.es.jma.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.es.jma.home.HomeScreen
import com.es.jma.ui.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable sealed interface HomeKey : NavKey
@Serializable data object HomeRoute : HomeKey

fun EntryProviderScope<NavKey>.homeEntry(navigator: Navigator) {
    entry<HomeRoute> {
        HomeScreen()
    }
}