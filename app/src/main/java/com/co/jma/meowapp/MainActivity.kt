package com.co.jma.meowapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.co.jma.meowapp.ui.MeowApp
import com.co.jma.meowapp.ui.theme.MeowAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeowAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MeowApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}