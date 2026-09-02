package com.es.jma.designsystem.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun CatImage(
    catImage: String,
    modifier: Modifier = Modifier,
    scale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = catImage,
        contentDescription = null,
        modifier = modifier,
        contentScale = scale
    )
}