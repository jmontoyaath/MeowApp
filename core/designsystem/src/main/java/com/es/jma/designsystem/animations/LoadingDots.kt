package com.es.jma.designsystem.animations

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.es.jma.designsystem.theme.MeowMotion.SLOW
import com.es.jma.designsystem.theme.Spacing.sm
import com.es.jma.designsystem.theme.Spacing.xsPlus

private const val DOT_COUNT = 3
private const val DOT_MIN_SCALE = 0.6f
private const val DOT_MAX_SCALE = 1f
private const val DOT_STAGGER_MILLIS = 150

@Composable
fun LoadingDots(
    modifier: Modifier = Modifier,
    dotColor: Color = MaterialTheme.colorScheme.primary,
    dotSize: Dp = sm
) {
    val transition = rememberInfiniteTransition(label = "dotsLoading")

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(xsPlus)) {
        repeat(times = DOT_COUNT) { index ->
            val scale by transition.animateFloat(
                initialValue = DOT_MIN_SCALE,
                targetValue = DOT_MAX_SCALE,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = SLOW, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse,
                    initialStartOffset = StartOffset(offsetMillis = index * DOT_STAGGER_MILLIS)
                ),
                label = "dotScale$index"
            )

            Box(
                modifier = Modifier
                    .size(dotSize)
                    .scale(scale)
                    .clip(CircleShape)
                    .background(dotColor)
            )
        }
    }
}