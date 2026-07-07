package com.es.jma.search.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.es.jma.search.SearchScreen
import com.es.jma.ui.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable sealed interface SearchKey : NavKey
@Serializable data object SearchRoute : SearchKey

fun EntryProviderScope<NavKey>.searchEntry(navigator: Navigator) {
    entry<SearchRoute> {
        SearchScreen(
            onBackClick = { navigator.goBack() }
        )
    }
}