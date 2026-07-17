package com.es.jma.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.es.jma.designsystem.components.BackButton
import com.es.jma.designsystem.icon.IconSize
import com.es.jma.designsystem.icon.MeowIcons
import com.es.jma.designsystem.meowPlaceHolder
import com.es.jma.designsystem.theme.CharcoalBlack
import com.es.jma.designsystem.theme.Pink80
import com.es.jma.designsystem.theme.iconSmallPressedAreaSize
import com.es.jma.designsystem.theme.marginDefault
import com.es.jma.designsystem.theme.marginSmall
import com.es.jma.designsystem.theme.roundedCornerShape50
import com.es.jma.designsystem.theme.smallBorderWidth

@Composable
fun TitleColumn(
    modifier: Modifier = Modifier,
    title: String = "",
    onCloseClick: (() -> Unit)? = null,
    onBackButton: (() -> Unit)? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    showSeparator: Boolean = true,
    isLoading: Boolean = false,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    val localFocusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(marginDefault),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            onBackButton?.let {
                BackButton(
                    onClick = it,
                    tint = CharcoalBlack,
                    size = IconSize.Small
                )
            } ?: Spacer(modifier = Modifier.size(iconSmallPressedAreaSize))
            Text(
                text = title,
                modifier = Modifier
                    .weight(1f)
                    .meowPlaceHolder(isLoading, roundedCornerShape50),
                textAlign = TextAlign.Center,
                color = CharcoalBlack
            )
            IconButton(
                onClick = onCloseClick ?: {},
                modifier = Modifier
                    .alpha(if (onCloseClick == null) 0f else 1f)
            ) {
                Icon(
                    imageVector = MeowIcons.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .size(iconSmallPressedAreaSize)
                        .padding(marginSmall)
                        .clip(CircleShape)
                        .meowPlaceHolder(isLoading, roundedCornerShape50)
                )
            }
        }
        if (showSeparator) HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .zIndex(100f),
            thickness = smallBorderWidth,
            color = Pink80
        )
        Column(modifier, verticalArrangement, horizontalAlignment) {
            content()
        }
    }
}

const val title = "Title"

@Preview(showBackground = true)
@Composable
fun PreviewColumn() {
    TitleColumn(title = title, onCloseClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewColumnWithBack() {
    TitleColumn(
        title = title,
        onBackButton = {},
        onCloseClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewColumnWithBackNoTitle() {
    TitleColumn(
        title = "",
        onBackButton = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSkeleton() {
    TitleColumn(
        isLoading = true,
        title = title,
        onCloseClick = {}
    )
}