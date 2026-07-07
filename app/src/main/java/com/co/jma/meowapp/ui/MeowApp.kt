package com.co.jma.meowapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.co.jma.meowapp.R
import com.co.jma.meowapp.navigation.TOP_LEVEL_NAV_ITEMS
import com.es.jma.designsystem.components.AppBar
import com.es.jma.favorite.navigation.FavoriteRoute
import com.es.jma.favorite.navigation.favoriteEntry
import com.es.jma.home.navigation.HomeRoute
import com.es.jma.home.navigation.homeEntry
import com.es.jma.search.navigation.SearchRoute
import com.es.jma.search.navigation.searchEntry
import com.es.jma.ui.navigation.Navigator
import com.es.jma.ui.navigation.toEntries

@Composable
fun MeowApp(
    appState: MeowState,
    modifier: Modifier = Modifier,
    onOpenWiki: (url: String) -> Unit = {},
) {

    val navigator = remember { Navigator(appState.navigationState) }
    val currentTopLevelKey = appState.navigationState.currentTopLevelKey
    val bottomBarKeys = listOf(HomeRoute, SearchRoute, FavoriteRoute)

    MaterialTheme {
        Scaffold(
            topBar = {
                AppBar(
                    title = stringResource(id = R.string.app_name),
                    onBackClick = null,
                    actions = null
                )
            },
            bottomBar = {
                NavigationBar {
                    bottomBarKeys.forEach { key ->
                        val item = TOP_LEVEL_NAV_ITEMS.getValue(key)
                        val selected = currentTopLevelKey == key
                        NavigationBarItem(
                            selected = selected,
                            onClick = { navigator.navigate(key) },
                            icon = {
                                Icon(
                                    imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = stringResource(item.iconTextId),
                                )
                            },
                            label = { Text(stringResource(item.titleTextId)) },
                        )
                    }
                }
            },
        ) { contentPadding ->
            Surface(modifier = modifier.padding(paddingValues = contentPadding)) {
                Column {

                    val entryProvider = entryProvider {
                        homeEntry(navigator)
                        searchEntry(navigator)
                        favoriteEntry(navigator)
                    }

                    NavDisplay(
                        entries = appState.navigationState.toEntries(entryProvider),
                        onBack = { navigator.goBack() },
                    )
                }
            }
        }
    }
}