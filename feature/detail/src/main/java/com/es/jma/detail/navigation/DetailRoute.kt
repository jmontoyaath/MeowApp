package com.es.jma.detail.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.DialogSceneStrategy
import com.es.jma.detail.DetailScreen
import com.es.jma.ui.navigation.Navigator
import kotlinx.serialization.Serializable

@Serializable sealed interface DetailKey : NavKey
@Serializable data class DetailRoute(val breedId: String, val catImage: String) : DetailKey

fun EntryProviderScope<NavKey>.detailEntry(navigator: Navigator) {
    entry<DetailRoute>(
        metadata = DialogSceneStrategy.dialog()
    ) { key ->
        DetailScreen(
            idBreed = key.breedId,
            imageCat = key.catImage,
            onDismiss = { navigator.goBack() }
        )
    }
}