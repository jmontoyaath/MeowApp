package com.es.jma.search.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.es.jma.search.SearchScreen
import com.es.jma.navigation.Navigator
import com.es.jma.navigation.SearchRoute

fun EntryProviderScope<NavKey>.searchEntry(navigator: Navigator) {
    entry<SearchRoute> {
        SearchScreen()
    }
}