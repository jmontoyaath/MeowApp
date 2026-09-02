package com.es.jma.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.es.jma.designsystem.animations.LoadingDots
import com.es.jma.designsystem.theme.marginLarge
import com.es.jma.designsystem.theme.marginLarger
import com.es.jma.designsystem.theme.marginOne
import com.es.jma.designsystem.theme.marginTinier
import com.es.jma.designsystem.theme.marginZero

enum class ButtonType {
    PRIMARY, SECONDARY, TEXT
}

@Composable
fun MeowButton(
    label: String,
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.PRIMARY,
    onClick: () -> Unit,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    leftIcon: @Composable (() -> Unit)? = null,
    height: Dp = marginLarger,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    border: Dp = marginOne,
    isTextLoading: Boolean = false
) {
    when (buttonType) {
        ButtonType.SECONDARY -> {
            SecondaryButton(
                modifier = modifier,
                label = label,
                onClick = onClick,
                contentPadding = contentPadding,
                leftIcon = leftIcon,
                height = height,
                border = border
            )
        }

        ButtonType.TEXT -> {
            TertiaryButton(
                modifier = modifier,
                label = label,
                onClick = onClick,
                leftIcon = leftIcon,
                height = height,
                enabled = enabled,
                isLoading = isLoading,
                isTextLoading = isTextLoading
            )
        }

        else -> {
            PrimaryButton(
                modifier = modifier,
                label = label,
                onClick = onClick,
                contentPadding = contentPadding,
                leftIcon = leftIcon,
                height = height,
                enabled = enabled,
                isLoading = isLoading
            )
        }
    }
}

@Composable
fun PrimaryButton(
    modifier: Modifier,
    label: String,
    onClick: () -> Unit,
    contentPadding: PaddingValues,
    leftIcon: @Composable (() -> Unit)?,
    height: Dp,
    enabled: Boolean,
    isLoading: Boolean
) {
    val interactionSource = remember { MutableInteractionSource() }

    Button (
        onClick = { if(!isLoading) onClick() },
        interactionSource = interactionSource,
        modifier = modifier.height(height),
        shape = RoundedCornerShape(marginLarge),
        contentPadding = contentPadding,
        enabled = enabled,
        elevation = ButtonDefaults.elevatedButtonElevation(marginZero),
    ) {
        Row(
            horizontalArrangement = spacedBy(marginTinier),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leftIcon?.invoke()
            if(isLoading) {
                LoadingDots()
            } else {
                Text(
                    text = label
                )
            }
        }
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier,
    label: String,
    onClick: () -> Unit,
    contentPadding: PaddingValues,
    leftIcon: @Composable (() -> Unit)?,
    height: Dp,
    border: Dp
) {
    val interactionSource = remember { MutableInteractionSource() }

    OutlinedButton(
        onClick = onClick,
        interactionSource = interactionSource,
        modifier = modifier.height(height),
        shape = RoundedCornerShape(marginLarge),
        border = BorderStroke(color = MaterialTheme.colorScheme.secondary, width = border),
        contentPadding = contentPadding,
        elevation = ButtonDefaults.elevatedButtonElevation(marginZero)
    ) {
        Row(
            horizontalArrangement = spacedBy(marginTinier),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leftIcon?.invoke()
            Text(
                text = label
            )
        }
    }
}

@Composable
fun TertiaryButton(
    modifier: Modifier,
    label: String,
    onClick: () -> Unit,
    leftIcon: @Composable (() -> Unit)?,
    height: Dp,
    enabled: Boolean,
    isLoading: Boolean,
    isTextLoading: Boolean
) {
    TextButton(
        onClick = { if(!isLoading) onClick() },
        modifier = modifier.height(height),
        elevation = ButtonDefaults.elevatedButtonElevation(marginZero),
        enabled = enabled
    ) {
        Row(
            horizontalArrangement = spacedBy(marginTinier),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leftIcon?.invoke()
            when {
                isLoading -> LoadingContent()
                isTextLoading -> LoadingDots()
                else -> {
                    Text(
                        text = label,
                    )
                }
            }
        }
    }
}