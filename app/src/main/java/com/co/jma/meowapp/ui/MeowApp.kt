package com.co.jma.meowapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.co.jma.meowapp.R
import com.es.jma.designsystem.theme.MeowAppTheme
import com.es.jma.designsystem.components.AppBar
import com.es.jma.favorite.navigation.favoriteEntry
import com.es.jma.home.navigation.homeEntry
import com.es.jma.search.navigation.searchEntry
import com.es.jma.ui.navigation.Navigator
import com.es.jma.ui.navigation.toEntries

@Composable
fun MeowApp(
    appState: MeowState,
    modifier: Modifier = Modifier,
    onOpenWiki: (url: String) -> Unit = {}) {

    val navigator = remember { Navigator(appState.navigationState) }

    MaterialTheme {
        Scaffold(topBar = {
            AppBar(title = stringResource(id = R.string.app_name), onBackClick = null, actions = null)
        }) { contentPadding ->
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