package com.es.jma.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.es.jma.designsystem.theme.marginDefault
import com.es.jma.model.CatInfo
import com.es.jma.ui.views.CatInformation

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.action.collect { action ->
            when (action) {
                else -> Unit
            }
        }
    }

    when {
        state.loading -> FavoriteScreenLoading()
        state.showError -> FavoriteScreenError()
        else -> {
            if (state.cats.isNullOrEmpty())
                FavoriteScreenEmpty()
            else FavoriteScreenContent(
                cats = state.cats!!,
                modifier = modifier,
                onDeleteClicked = viewModel::onDeleteFavorite
            )
        }
    }
}

@Composable
fun FavoriteScreenContent(
    cats: List<CatInfo>,
    modifier: Modifier = Modifier,
    onCatClicked: (CatInfo) -> Unit = {},
    onDeleteClicked: (CatInfo) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = marginDefault)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(marginDefault)
    ) {
        items(cats, key = { it.id }) { cat ->
            CatInformation(
                cat = cat,
                onCatClicked = onCatClicked,
                onDeletedClicked = onDeleteClicked
            )
        }
    }
}

@Composable
fun FavoriteScreenEmpty() {
    Text(text = "No kittens here")
}

@Composable
fun FavoriteScreenError() {
    Text(text = "There is no cat in the bag")
}

@Composable
fun FavoriteScreenLoading() {
    Text(text = "Searching for cats")
}