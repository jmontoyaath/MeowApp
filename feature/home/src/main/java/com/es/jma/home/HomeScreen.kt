package com.es.jma.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.es.jma.designsystem.theme.marginDefault
import com.es.jma.model.CatInfo
import com.es.jma.ui.CatInformation
import com.es.jma.ui.PagingAppendState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val catsPage = state.catsPaged.collectAsLazyPagingItems()

    LaunchedEffect(viewModel) {
        viewModel.action.collect { action ->
            when (action) {
                else -> Unit
            }
        }
    }

    when (catsPage.loadState.refresh) {
        is LoadState.Loading -> HomeScreenLoading()
        is LoadState.Error -> HomeScreenError()
        else -> HomeScreenContent(
            catsPage = catsPage,
            modifier = modifier,
            onCatClicked = viewModel::onCatClicked,
            onFavoriteClicked = viewModel::onFavoriteClicked
        )
    }
}

@Composable
fun HomeScreenContent(
    catsPage: LazyPagingItems<CatInfo>,
    modifier: Modifier = Modifier,
    onCatClicked: (CatInfo) -> Unit = {},
    onFavoriteClicked: (CatInfo, Boolean) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = marginDefault)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(marginDefault)
    ) {
        items(
            count = catsPage.itemCount,
            key = catsPage.itemKey { it.id }
        ) { index ->
            catsPage[index]?.let { cat ->
                CatInformation(
                    cat = cat,
                    onCatClicked = onCatClicked,
                    onFavoriteClicked = onFavoriteClicked
                )
            }
        }

        item {
            PagingAppendState(
                loadState = catsPage.loadState.append,
                onRetry = { catsPage.retry() }
            )
        }
    }
}

@Composable
fun HomeScreenError() {
    Text(text = "There is no cat in the bag")
}

@Composable
fun HomeScreenLoading() {
    Text(text = "Searching for cats")
}