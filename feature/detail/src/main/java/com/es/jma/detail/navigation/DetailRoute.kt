package com.es.jma.detail.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.DialogSceneStrategy
import com.es.jma.detail.DetailScreen
import com.es.jma.navigation.DetailRoute
import com.es.jma.navigation.Navigator

fun EntryProviderScope<NavKey>.detailEntry(navigator: Navigator, onOpenWiki: (url: String) -> Unit = {}) {
    entry<DetailRoute>(
        metadata = DialogSceneStrategy.dialog()
    ) { key ->
        DetailScreen(
            idBreed = key.breedId,
            imageCat = key.catImage,
            onDismiss = { navigator.goBack() },
            onOpenWiki = onOpenWiki
        )
    }
}