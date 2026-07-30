package com.es.jma.designsystem.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.es.jma.designsystem.icon.MeowIcons
import com.es.jma.designsystem.theme.MeowAppTheme
import com.es.jma.designsystem.theme.smallIconPressArea

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeowAppBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            MeowIconButton(
                icon = MeowIcons.ArrowBack,
                modifier = Modifier.size(smallIconPressArea),
                onClick = onBackClick
            )
        },
        actions = actions ?: {},
    )
}

@Preview(name = "AppBar Preview", showBackground = true)
@Composable
private fun MeowAppBarWhitePreview() {
    MeowAppTheme {
        MeowAppBar(title = "AppBar Title", onBackClick = {})
    }
}

@Preview(name = "AppBar No Back Preview", showBackground = true)
@Composable
fun MeowAppBarWhiteNoBackPreview() {
    MeowAppTheme {
        MeowAppBar(title = "AppBar Title")
    }
}

@Preview(name = "AppBar No Back Preview and Action", showBackground = true)
@Composable
fun MeowAppBarWhiteActionPreview() {
    MeowAppTheme {
        MeowAppBar(title = "AppBar Title", actions = {
            MeowIconButton(
                icon = MeowIcons.SearchOutLine,
                modifier = Modifier.size(smallIconPressArea),
                onClick = {}
            )
        })
    }
}