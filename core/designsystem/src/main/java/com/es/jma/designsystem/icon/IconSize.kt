package com.es.jma.designsystem.icon

import androidx.compose.ui.unit.Dp
import com.es.jma.designsystem.theme.normalIconPressArea
import com.es.jma.designsystem.theme.smallIconPressArea

enum class IconSize(val value: Dp) {
    Small(smallIconPressArea),
    Normal(normalIconPressArea)
}