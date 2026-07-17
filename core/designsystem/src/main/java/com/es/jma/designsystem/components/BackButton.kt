package com.es.jma.designsystem.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.es.jma.designsystem.icon.IconSize
import com.es.jma.designsystem.icon.MeowIcons
import com.es.jma.designsystem.theme.normalIconSize
import com.es.jma.designsystem.theme.smallIconSize

@Composable
fun BackButton(
    onClick: () -> Unit,
    tint: Color = Color.Unspecified,
    size: IconSize = IconSize.Normal
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(size.value)
    ) {
        Icon(
            imageVector = MeowIcons.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .size(when(size) {
                    IconSize.Normal -> normalIconSize
                    IconSize.Small -> smallIconSize
                })
                .clip(CircleShape),
            tint = tint
        )
    }
}