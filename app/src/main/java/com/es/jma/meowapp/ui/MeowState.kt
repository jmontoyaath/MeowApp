package com.es.jma.meowapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.es.jma.meowapp.navigation.TOP_LEVEL_NAV_ITEMS
import com.es.jma.meowapp.navigation.meowNavConfig
import com.es.jma.home.navigation.HomeRoute
import com.es.jma.ui.navigation.NavigationState
import com.es.jma.ui.navigation.rememberNavigationState
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberMeowAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): MeowState {
    val navigationState = rememberNavigationState(
        startKey = HomeRoute,
        topLevelKeys = TOP_LEVEL_NAV_ITEMS.keys,
        navConfig = meowNavConfig,
    )

    return remember(navigationState, coroutineScope) {
        MeowState(navigationState = navigationState, coroutineScope = coroutineScope)
    }
}

@Stable
class MeowState (
    val navigationState: NavigationState,
    coroutineScope: CoroutineScope,
) {

}