package com.co.jma.meowapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.co.jma.meowapp.ui.MeowApp
import com.co.jma.meowapp.ui.rememberMeowAppState
import com.es.jma.designsystem.theme.MeowAppTheme
import com.es.jma.ui.openCustomTab
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeowAppTheme {
                val appState = rememberMeowAppState()
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