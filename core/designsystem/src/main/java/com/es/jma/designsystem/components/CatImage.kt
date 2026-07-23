package com.es.jma.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.es.jma.designsystem.theme.imageSize
import com.es.jma.designsystem.theme.marginTiny
import com.es.jma.designsystem.theme.marginZero

@Composable
fun CatImage(
    catImage: String,
) {
    AsyncImage(
        model = catImage,
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .size(imageSize)
            .clip(
                RoundedCornerShape(
                    topEnd = marginTiny,
                    topStart = marginTiny,
                    bottomEnd = marginZero,
                    bottomStart = marginZero
                )
            ),
        contentScale = ContentScale.Crop
    )
}