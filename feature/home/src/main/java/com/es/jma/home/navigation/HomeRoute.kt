package com.es.jma.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.es.jma.home.HomeScreen
import com.es.jma.navigation.DetailRoute
import com.es.jma.navigation.HomeRoute
import com.es.jma.navigation.Navigator

fun EntryProviderScope<NavKey>.homeEntry(navigator: Navigator) {
    entry<HomeRoute> {
        HomeScreen(onCatClicked = { breedId, catId ->
            navigator.navigate(DetailRoute(breedId, catId))
        })
    }
}