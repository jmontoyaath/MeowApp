package com.es.jma.designsystem.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import com.es.jma.designsystem.theme.iconSmallPressedAreaSize
import com.es.jma.designsystem.theme.smallIconSize

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
                    modifier = Modifier.size(smallIconSize)
                )
            }
        },
        actions = actions ?: {}
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

@Preview(name = "AppBar No Back Preview and Action", showBackground = true)
@Composable
fun AppBarWhiteActionPreview() {
    MaterialTheme {
        AppBar(title = "AppBar Title", actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = MeowIcons.SearchOutLine,
                    contentDescription = null,
                    modifier = Modifier.size(iconSmallPressedAreaSize)
                )
            }
        })
    }
}