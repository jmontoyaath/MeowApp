package com.co.jma.meowapp.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import com.es.jma.favorite.navigation.FavoriteRoute
import com.es.jma.home.navigation.HomeRoute
import com.es.jma.search.navigation.SearchRoute
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val meowNavConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomeRoute::class, HomeRoute.serializer())
            subclass(FavoriteRoute::class, FavoriteRoute.serializer())
        }
    }
}