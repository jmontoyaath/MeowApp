package com.es.jma.designsystem.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.es.jma.designsystem.theme.marginOne
import com.es.jma.designsystem.theme.marginSmallest

@Composable
fun MeowChips(
    text: String,
    modifier: Modifier = Modifier,
    maxVisible: Int = 3
) {
    val traits = remember(text) {
        text.split(",").map { it.trim() }.filter { it.isNotBlank() }
    }
    var expanded by rememberSaveable(text) { mutableStateOf(false) }

    val visible = if (expanded) traits else traits.take(maxVisible)
    val remaining = traits.size - maxVisible

    FlowRow(
        modifier = modifier.animateContentSize(),
        horizontalArrangement = Arrangement.spacedBy(marginSmallest),
        verticalArrangement = Arrangement.spacedBy(marginSmallest)
    ) {
        visible.forEach { trait ->
            AssistChip(
                onClick = { expanded = true },
                label = { Text(trait, style = MaterialTheme.typography.labelSmall) }
            )
        }
        if (!expanded && remaining > 0) {
            AssistChip(
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    labelColor = MaterialTheme.colorScheme.onSecondary
                ),
                border = BorderStroke(
                    width = marginOne,
                    color = MaterialTheme.colorScheme.onSecondary
                ),
                onClick = { expanded = true },
                label = { Text("+$remaining", style = MaterialTheme.typography.labelSmall) }
            )
        } else {
            MeowButton(
                label = "show less",
                buttonType = ButtonType.TEXT,
                onClick = { expanded = false }
            )
        }
    }
}