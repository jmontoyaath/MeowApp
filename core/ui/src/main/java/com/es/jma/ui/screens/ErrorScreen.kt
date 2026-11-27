package com.es.jma.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.es.jma.designsystem.components.MeowButton
import com.es.jma.designsystem.theme.ComponentSize.buttonHeight
import com.es.jma.designsystem.theme.Spacing.lg
import com.es.jma.ui.R
import com.es.jma.ui.animation.CatAnimationLottie

@Composable
fun ErrorScreen(
    onClickReTry: (() -> Unit)? = null
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Box(modifier = Modifier.padding(all = lg).fillMaxWidth()) {
            CatAnimationLottie(
                animation = R.raw.cat_playing
            )
        }
        Text(text = stringResource(id = R.string.error_message), style = MaterialTheme.typography.bodyMedium)
        onClickReTry?.let {
            Row(modifier = Modifier.padding(all = lg)) {
                MeowButton(
                    onClick = onClickReTry,
                    label = stringResource(id = R.string.try_button),
                    height = buttonHeight,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}