package com.es.jma.designsystem.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.es.jma.designsystem.theme.marginZero

@Composable
fun MeowIconButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    ting: Color = MaterialTheme.colorScheme.onSurface,
    onClick: (() -> Unit)? = null
) {
    onClick?.let { click ->
        IconButton(onClick = click) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = modifier,
                tint = ting
            )
        }
    }
}

@Composable
fun MeowIconButtonFilled(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    onClick?.let { click ->
        FloatingActionButton(
            onClick = click,
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary,
            elevation = FloatingActionButtonDefaults.elevation(marginZero)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = modifier
            )
        }
    }
}