package com.es.jma.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.es.jma.designsystem.theme.imageCarrouselCat
import com.es.jma.designsystem.theme.marginBig
import com.es.jma.designsystem.theme.marginSmall
import com.es.jma.designsystem.theme.marginSmaller
import com.es.jma.designsystem.theme.marginTiny

@Composable
fun BreedImageCarousel(images: List<String>) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    Column(modifier = Modifier.padding(top = marginSmaller)) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = marginBig),
            pageSpacing = marginSmaller,
            modifier = Modifier.fillMaxWidth().height(imageCarrouselCat)
        ) { page ->
            AsyncImage(
                model = images[page],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(marginSmall))
            )
        }

        if (images.size > 1) {
            Row (
                modifier = Modifier.fillMaxWidth().padding(top = marginTiny),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(images.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .padding(horizontal = marginTiny)
                            .size(if (isSelected) marginSmaller else marginTiny)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                            )
                    )
                }
            }
        }
    }
}