package com.es.jma.ui.animation

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

private const val defaultAnimationScale = 1f

@Composable
fun CatAnimationLottie(
    modifier: Modifier = Modifier,
    @RawRes animation: Int,
    iterations: Int = LottieConstants.IterateForever,
    animationScale: Float = defaultAnimationScale,
    onAnimationFinished: () -> Unit = {}
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animation))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = iterations,
    )

    LaunchedEffect(progress) {
        if (progress == 1f) {
            onAnimationFinished()
        }
    }
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier.scale(animationScale),
    )
}