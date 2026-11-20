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
import androidx.compose.ui.res.stringResource
import com.es.jma.designsystem.R
import com.es.jma.designsystem.theme.Spacing.xsPlus
import com.es.jma.designsystem.theme.marginOne
import com.es.jma.utils.constants.ZERO
import com.es.jma.utils.constants.COMMA

private const val NUMBER_CHIPS = 3

@Composable
fun MeowChips(
    text: String,
    modifier: Modifier = Modifier,
    maxVisible: Int = NUMBER_CHIPS
) {
    val traits = remember(text) {
        text.split(COMMA).map { it.trim() }.filter { it.isNotBlank() }
    }
    var expanded by rememberSaveable(text) { mutableStateOf(false) }

    val visible = if (expanded) traits else traits.take(maxVisible)
    val remaining = traits.size - maxVisible

    FlowRow(
        modifier = modifier.animateContentSize(),
        horizontalArrangement = Arrangement.spacedBy(xsPlus),
        verticalArrangement = Arrangement.spacedBy(xsPlus)
    ) {
        visible.forEach { trait ->
            AssistChip(
                onClick = { expanded = true },
                label = { Text(trait, style = MaterialTheme.typography.labelSmall) }
            )
        }

        when {
            !expanded && remaining > ZERO -> {
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
            }
            expanded -> {
                MeowButton(
                    label = stringResource(id = R.string.show_less),
                    buttonType = ButtonType.TEXT,
                    onClick = { expanded = false }
                )
            }
            else -> Unit
        }
    }
}