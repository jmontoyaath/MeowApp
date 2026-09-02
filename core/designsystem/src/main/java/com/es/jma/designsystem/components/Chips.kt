package com.es.jma.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.es.jma.designsystem.theme.marginSmallest

@Composable
fun MeowChips(
    text: String,
    modifier: Modifier = Modifier,
    maxVisible: Int = 3,
    onClick: ((String) -> Unit)? = null
) {
    val traits = remember(text) {
        text.split(",").map { it.trim() }.filter { it.isNotBlank() }
    }
    val visible = traits.take(maxVisible)
    val remaining = traits.size - visible.size

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(marginSmallest)
    ) {
        visible.forEach { trait ->
            AssistChip(
                onClick = { onClick?.invoke(trait) },
                label = { Text(trait, style = MaterialTheme.typography.labelSmall) }
            )
        }
        if (remaining > 0) {
            AssistChip(
                onClick = {},
                label = { Text("+$remaining", style = MaterialTheme.typography.labelSmall) }
            )
        }
    }
}