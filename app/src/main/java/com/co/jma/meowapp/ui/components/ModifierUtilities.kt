package com.co.jma.meowapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.modifyIf(condition: Boolean, modify: @Composable Modifier.() -> Modifier) =
    if (condition) modify() else this