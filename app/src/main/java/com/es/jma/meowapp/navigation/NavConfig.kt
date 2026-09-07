package com.es.jma.meowapp.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import com.es.jma.navigation.DetailRoute
import com.es.jma.navigation.FavoriteRoute
import com.es.jma.navigation.HomeRoute
import com.es.jma.navigation.SearchRoute
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val meowNavConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomeRoute::class)
            subclass(SearchRoute::class)
            subclass(FavoriteRoute::class)
            subclass(DetailRoute::class)
        }
    }
}