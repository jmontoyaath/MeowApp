package com.es.jma.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable sealed interface HomeKey : NavKey
@Serializable data object HomeRoute : HomeKey

@Serializable sealed interface SearchKey : NavKey
@Serializable data object SearchRoute : SearchKey

@Serializable sealed interface FavoriteKey : NavKey
@Serializable data object FavoriteRoute : FavoriteKey

@Serializable sealed interface DetailKey : NavKey
@Serializable data class DetailRoute(val breedId: String, val catImage: String) : DetailKey

@Serializable sealed interface SettingsKey : NavKey
@Serializable data object SettingsRoute : SettingsKey