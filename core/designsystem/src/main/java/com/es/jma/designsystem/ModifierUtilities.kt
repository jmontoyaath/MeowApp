package com.es.jma.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.wear.compose.material3.PlaceholderState
import androidx.wear.compose.material3.placeholder
import com.es.jma.designsystem.theme.SageWhite

@Composable
fun Modifier.modifyIf(condition: Boolean, modify: @Composable Modifier.() -> Modifier) =
    if (condition) modify() else this

fun Modifier.meowPlaceHolder(visible: Boolean = true, shape: Shape = RectangleShape) = composed {
    this.placeholder(
        placeholderState = PlaceholderState(isVisible = visible),
        color = SageWhite,
        shape = shape
    )
}