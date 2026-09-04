package com.es.jma.meowapp.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val meowNavConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomeRoute::class, HomeRoute.serializer())
            subclass(SearchRoute::class, SearchRoute.serializer())
            subclass(FavoriteRoute::class, FavoriteRoute.serializer())
            subclass(DetailRoute::class, DetailRoute.serializer())
        }
    }
}