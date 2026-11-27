package com.es.jma.ui.views

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.layout.ContentScale
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import com.es.jma.designsystem.components.CatImage
import com.es.jma.designsystem.theme.ComponentSize.carouselImage
import com.es.jma.designsystem.theme.MeowAlpha.INACTIVE
import com.es.jma.designsystem.theme.Spacing.md
import com.es.jma.designsystem.theme.Spacing.sm
import com.es.jma.designsystem.theme.Spacing.xs
import com.es.jma.designsystem.theme.Spacing.xsPlus
import com.es.jma.designsystem.theme.Spacing.xxl
import com.es.jma.designsystem.theme.primaryLight
import com.es.jma.designsystem.theme.tertiaryContainerDark

private const val VERTICES_NUM = 6
private const val VERTICES_NUM_DIVIDER = 2

@Composable
fun BreedImageCarousel(images: List<String>) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    Column(modifier = Modifier.padding(top = sm)) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = xxl),
            pageSpacing = sm,
            modifier = Modifier.fillMaxWidth().height(carouselImage)
        ) { page ->
            CatImage(
                catImage = images[page],
                scale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(size = md))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )
        }

        if (images.size > 1) {
            Row (
                modifier = Modifier.fillMaxWidth().padding(top = xs),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(images.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .drawWithCache {
                                val roundedPolygon = RoundedPolygon(
                                    numVertices = VERTICES_NUM,
                                    radius = size.minDimension / VERTICES_NUM_DIVIDER,
                                    centerX = size.width / VERTICES_NUM_DIVIDER,
                                    centerY = size.height / VERTICES_NUM_DIVIDER
                                )
                                val roundedPolygonPath = roundedPolygon.toPath().asComposePath()
                                onDrawBehind {
                                    drawPath(roundedPolygonPath, color = if (isSelected) primaryLight
                                    else tertiaryContainerDark.copy(alpha = INACTIVE))
                                }
                            }
                            .padding(horizontal = xs, vertical = xsPlus)
                            .size(if (isSelected) sm else xs)
                    )
                }
            }
        }
    }
}

