package com.es.jma.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.es.jma.designsystem.theme.imageSize

@Composable
fun CatImage(
    catImage: String
) {
    AsyncImage(
        model = catImage,
        contentDescription = "",
        modifier = Modifier
            .size(imageSize)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}