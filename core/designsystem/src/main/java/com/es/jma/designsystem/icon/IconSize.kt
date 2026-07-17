package com.es.jma.designsystem.icon

import androidx.compose.ui.unit.Dp
import com.es.jma.designsystem.theme.iconNormalPressedAreaSize
import com.es.jma.designsystem.theme.iconSmallPressedAreaSize

enum class IconSize(val value: Dp) {
    Small(iconSmallPressedAreaSize),
    Normal(iconNormalPressedAreaSize)
}