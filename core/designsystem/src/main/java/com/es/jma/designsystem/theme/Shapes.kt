package com.es.jma.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import com.es.jma.designsystem.theme.Spacing.lg
import com.es.jma.designsystem.theme.Spacing.md
import com.es.jma.designsystem.theme.Spacing.sm
import com.es.jma.designsystem.theme.Spacing.xs
import com.es.jma.designsystem.theme.Spacing.xxl

val MeowShapes = Shapes(
    extraSmall = RoundedCornerShape(size = xs),
    small = RoundedCornerShape(size = sm),
    medium = RoundedCornerShape(size = md),
    large = RoundedCornerShape(size = lg),
    extraLarge = RoundedCornerShape(size = xxl)
)