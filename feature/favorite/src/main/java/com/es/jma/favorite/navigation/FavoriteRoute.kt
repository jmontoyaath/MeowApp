package com.es.jma.favorite.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.es.jma.favorite.FavoriteScreen
import com.es.jma.ui.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable
data object FavoriteRoute : NavKey

fun EntryProviderScope<NavKey>.favoriteEntry(navigator: Navigator) {
    entry<FavoriteRoute> {
        FavoriteScreen()
    }
}