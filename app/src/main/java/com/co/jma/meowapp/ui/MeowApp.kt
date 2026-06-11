package com.co.jma.meowapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.co.jma.meowapp.R
import com.co.jma.meowapp.ui.components.AppBar
import com.co.jma.meowapp.ui.theme.MeowAppTheme

@Composable
fun MeowApp(modifier: Modifier = Modifier) {
    MaterialTheme {
        Scaffold(topBar = {
            AppBar(title = stringResource(id = R.string.app_name), onBackClick = null, actions = null)
        }) { contentPadding ->
            Surface(modifier = modifier.padding(paddingValues = contentPadding)) {
                Column {

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeowAppPreview() {
    MeowAppTheme {
        MeowApp()
    }
}