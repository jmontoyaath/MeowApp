package com.es.jma.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.es.jma.ui.R
import com.es.jma.ui.animation.CatAnimationLottie

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CatAnimationLottie(
            animation = R.raw.cat_playing
        )
    }
}