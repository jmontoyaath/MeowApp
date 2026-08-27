package com.es.jma.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import com.es.jma.designsystem.theme.loadingSize
import com.es.jma.designsystem.theme.marginSmall
import com.es.jma.designsystem.theme.marginSmaller
import com.es.jma.model.Breed

@Composable
fun BreedInformation(
    breed: Breed,
    isExpanded: Boolean,
    images: List<String>,
    isLoadingImages: Boolean,
    onClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(marginSmall))
            .clickable(onClick = onClick)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth().padding(marginSmaller),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(marginSmall)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                breed.name?.let { Text(it, style = MaterialTheme.typography.titleMedium) }
                breed.temperament?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        AnimatedVisibility(visible = isExpanded) {
            when {
                isLoadingImages -> Box(
                    modifier = Modifier.fillMaxWidth().height(loadingSize),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }

                images.isEmpty() -> Box(
                    modifier = Modifier.fillMaxWidth().height(loadingSize),
                    contentAlignment = Alignment.Center
                ) { Text("Sin imágenes disponibles", style = MaterialTheme.typography.bodySmall) }

                else -> BreedImageCarousel(images = images)
            }
        }
    }
}