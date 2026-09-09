package com.es.jma.meowapp.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.es.jma.designsystem.components.BottomBarItem
import com.es.jma.designsystem.components.MeowAppBar
import com.es.jma.designsystem.components.MeowBottomBar
import com.es.jma.designsystem.components.MeowIconButton
import com.es.jma.designsystem.icon.MeowIcons
import com.es.jma.designsystem.theme.smallIconSize
import com.es.jma.detail.navigation.detailEntry
import com.es.jma.favorite.navigation.favoriteEntry
import com.es.jma.home.navigation.homeEntry
import com.es.jma.meowapp.R
import com.es.jma.meowapp.navigation.TOP_LEVEL_NAV_ITEMS
import com.es.jma.navigation.FavoriteRoute
import com.es.jma.navigation.HomeRoute
import com.es.jma.navigation.Navigator
import com.es.jma.navigation.SearchRoute
import com.es.jma.navigation.toEntries
import com.es.jma.search.navigation.searchEntry

@Composable
fun MeowApp(
    appState: MeowState,
    modifier: Modifier = Modifier,
    onOpenWiki: (url: String) -> Unit = {},
) {

    val navigator = remember { Navigator(appState.navigationState) }
    val currentTopLevelKey = appState.navigationState.currentTopLevelKey
    val bottomBarKeys = listOf(HomeRoute, SearchRoute, FavoriteRoute)
    val dialogSceneStrategy = remember { DialogSceneStrategy<NavKey>() }

    val currentKey = appState.navigationState.currentKey
    val isHomeScreen = currentKey is HomeRoute

    val snackBarHostState = remember { SnackbarHostState() }
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    val notConnectedMessage = stringResource(R.string.not_connected)
    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackBarHostState.showSnackbar(
                message = notConnectedMessage,
                duration = Indefinite,
            )
        }
    }

    Scaffold(
        topBar = {
            MeowAppBar(
                title = stringResource(id = R.string.app_name),
                onBackClick = if (isHomeScreen) null else navigator::goBack,
                actions = {
                    MeowIconButton(
                        icon = if (isSystemInDarkTheme()) MeowIcons.Sunny else MeowIcons.Night,
                        modifier = Modifier.size(smallIconSize),
                        onClick = { }
                    )
                }
            )
        },
        bottomBar = {
            MeowBottomBar(
                items = bottomBarKeys.map { key ->
                    val navItem = TOP_LEVEL_NAV_ITEMS.getValue(key)
                    BottomBarItem(
                        key = key.toString(),
                        icon = navItem.unselectedIcon,
                        selectedIcon = navItem.selectedIcon,
                        label = stringResource(navItem.titleTextId)
                    )
                },
                selectedKey = currentTopLevelKey.toString(),
                onItemClick = { key ->
                    val route = bottomBarKeys.first { it.toString() == key }
                    navigator.navigate(route)
                }
            )
        },
    ) { contentPadding ->
        Surface(modifier = modifier.padding(paddingValues = contentPadding)) {
            Column {
                val entryProvider = entryProvider {
                    homeEntry(navigator)
                    searchEntry(navigator)
                    favoriteEntry(navigator)
                    detailEntry(navigator, onOpenWiki)
                }

                NavDisplay(
                    entries = appState.navigationState.toEntries(entryProvider),
                    onBack = { navigator.goBack() },
                    sceneStrategies = listOf(dialogSceneStrategy)
                )
            }
        }
    }
}