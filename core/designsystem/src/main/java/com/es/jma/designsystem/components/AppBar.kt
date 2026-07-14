package com.es.jma.designsystem.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.es.jma.designsystem.icon.MeowIcons
import com.es.jma.designsystem.modifyIf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick ?: {}, modifier = Modifier.modifyIf(onBackClick == null) {
                alpha(0F)
            }) {
                Icon(
                    imageVector = MeowIcons.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = actions ?: {
            IconButton(onClick = {}, modifier = Modifier.alpha(0f)) {
                Icon(
                    imageVector = MeowIcons.Search,
                    contentDescription = null,
                )
            }
        }
    )
}

@Preview(name = "AppBar Preview", showBackground = true)
@Composable
private fun AppBarWhitePreview() {
    MaterialTheme {
        AppBar(title = "AppBar Title", onBackClick = {})
    }
}

@Preview(name = "AppBar No Back Preview", showBackground = true)
@Composable
fun AppBarWhiteNoBackPreview() {
    MaterialTheme {
        AppBar(title = "AppBar Title")
    }
}