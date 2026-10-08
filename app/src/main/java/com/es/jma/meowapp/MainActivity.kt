package com.es.jma.meowapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.es.jma.data.util.NetworkMonitor
import com.es.jma.meowapp.ui.MeowApp
import com.es.jma.meowapp.ui.rememberMeowAppState
import com.es.jma.designsystem.theme.MeowAppTheme
import com.es.jma.model.ThemeConfigEnum
import com.es.jma.ui.openCustomTab
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MeowViewModel by viewModels()

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState = it }
            }
        }

        setContent {
            MeowAppTheme(
                darkTheme = shouldUseDarkTheme(uiState)
            ) {
                val appState = rememberMeowAppState(
                    networkMonitor = networkMonitor
                )

                MeowApp(
                    appState = appState,
                    onOpenWiki = { url ->
                        this.openCustomTab(url = url)
                    }
                )
            }
        }
    }
}

@Composable
private fun shouldUseDarkTheme(uiState: MainActivityUiState): Boolean =
    when (uiState) {
        MainActivityUiState.Loading -> isSystemInDarkTheme()
        is MainActivityUiState.Success -> when (uiState.themeConfig) {
            ThemeConfigEnum.DARK -> true
            ThemeConfigEnum.LIGHT -> false
        }
    }