package com.es.jma.meowapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.es.jma.data.util.NetworkMonitor
import com.es.jma.meowapp.navigation.meowNavConfig
import com.es.jma.home.navigation.HomeRoute
import com.es.jma.meowapp.navigation.TOP_LEVEL_NAV_ITEMS
import com.es.jma.ui.navigation.NavigationState
import com.es.jma.ui.navigation.rememberNavigationState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberMeowAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    networkMonitor: NetworkMonitor,
): MeowState {
    val navigationState = rememberNavigationState(
        startKey = HomeRoute,
        topLevelKeys = TOP_LEVEL_NAV_ITEMS.keys,
        navConfig = meowNavConfig,
    )

    return remember(navigationState, coroutineScope) {
        MeowState(navigationState = navigationState, coroutineScope = coroutineScope, networkMonitor = networkMonitor)
    }
}

@Stable
class MeowState (
    val navigationState: NavigationState,
    coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor,
) {

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )
}