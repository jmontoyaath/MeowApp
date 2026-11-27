package com.es.jma.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import com.es.jma.designsystem.theme.Spacing.lg
import com.es.jma.designsystem.theme.Spacing.xl
import com.es.jma.ui.R

@Composable
fun PagingAppendState(
    loadState: LoadState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (loadState) {
        is LoadState.Loading -> LoadingMoreItem(modifier = modifier.fillMaxWidth())
        is LoadState.Error -> LoadMoreErrorItem(onRetry = onRetry, modifier = modifier.fillMaxWidth())
        is LoadState.NotLoading -> Unit
    }
}

@Composable
fun LoadingMoreItem(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = lg),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(xl))
    }
}

@Composable
fun LoadMoreErrorItem(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = lg),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = onRetry) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}