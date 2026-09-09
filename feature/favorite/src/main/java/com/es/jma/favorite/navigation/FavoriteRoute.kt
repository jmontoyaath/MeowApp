package com.es.jma.favorite.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.es.jma.favorite.FavoriteScreen
import com.es.jma.navigation.DetailRoute
import com.es.jma.navigation.FavoriteRoute
import com.es.jma.navigation.Navigator

fun EntryProviderScope<NavKey>.favoriteEntry(navigator: Navigator) {
    entry<FavoriteRoute> {
        FavoriteScreen(onCatClicked = { breedId, catId ->
            navigator.navigate(DetailRoute(breedId, catId))
        })
    }
}