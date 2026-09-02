package com.es.jma.designsystem.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.es.jma.designsystem.theme.bottomBarSize
import com.es.jma.designsystem.theme.marginZero

data class BottomBarItem<T>(
    val key: T,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val label: String,
)

@Composable
fun <T> MeowBottomBar(
    items: List<BottomBarItem<T>>,
    selectedKey: T,
    onItemClick: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier.height(bottomBarSize),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = marginZero
    ) {
        items.forEach { item ->
            val isSelected = item.key == selectedKey
            val tint by animateColorAsState(
                targetValue = if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurfaceVariant,
                animationSpec = tween(150),
                label = "bottomBarIconTint"
            )

            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemClick(item.key) },
                icon = {
                    AnimatedContent(
                        targetState = isSelected,
                        transitionSpec = { fadeIn(tween(120)) togetherWith fadeOut(tween(100)) },
                        label = "bottomBarIcon"
                    ) { selected ->
                        Icon(
                            imageVector = if (selected) item.selectedIcon else item.icon,
                            contentDescription = item.label,
                            tint = tint
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}