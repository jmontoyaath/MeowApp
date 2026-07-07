package com.co.jma.meowapp.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.es.jma.designsystem.icon.MeowIcons
import com.es.jma.favorite.R as favoriteR
import com.es.jma.favorite.navigation.FavoriteRoute
import com.es.jma.home.R as homeR
import com.es.jma.home.navigation.HomeRoute
import com.es.jma.search.R as searchR
import com.es.jma.search.navigation.SearchRoute

data class NavigationItem (
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
)

val HOME = NavigationItem(
    selectedIcon = MeowIcons.Home,
    unselectedIcon = MeowIcons.HomeOutLine,
    iconTextId = homeR.string.title,
    titleTextId = homeR.string.title
)

val SEARCH = NavigationItem(
    selectedIcon = MeowIcons.Search,
    unselectedIcon = MeowIcons.SearchOutLine,
    iconTextId = searchR.string.title,
    titleTextId = searchR.string.title,
)

val FAVORITE = NavigationItem(
    selectedIcon = MeowIcons.Favorite,
    unselectedIcon = MeowIcons.FavoriteOutLine,
    iconTextId = favoriteR.string.title,
    titleTextId = favoriteR.string.title,
)

val TOP_LEVEL_NAV_ITEMS = mapOf(
    HomeRoute to HOME,
    SearchRoute to SEARCH,
    FavoriteRoute to FAVORITE,
)