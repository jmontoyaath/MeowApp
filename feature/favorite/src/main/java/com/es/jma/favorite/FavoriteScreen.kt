package com.es.jma.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.es.jma.designsystem.theme.marginDefault
import com.es.jma.model.CatInfo
import com.es.jma.ui.animation.CatAnimationLottie
import com.es.jma.ui.screens.ErrorScreen
import com.es.jma.ui.screens.LoadingScreen
import com.es.jma.ui.views.CatInformation
import com.es.jma.ui.R as uiR

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel(),
    onCatClicked: (String, String) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    val errorMessage = stringResource(R.string.error_deleting_cat)
    LaunchedEffect(viewModel) {
        viewModel.action.collect { action ->
            when (action) {
                is FavoriteAction.ShowDetailModal -> onCatClicked(action.breedId, action.urlImage)
                is FavoriteAction.ShowErrorFavorites -> {
                    snackBarHostState.showSnackbar(
                        message = errorMessage,
                        duration = Indefinite,
                    )
                }
                else -> Unit
            }
        }
    }

    when {
        state.loading -> LoadingScreen()
        state.showError -> ErrorScreen(onClickReTry = viewModel::getFavorites)
        else -> {
            if (state.cats.isNullOrEmpty())
                FavoriteScreenEmpty()
            else FavoriteScreenContent(
                cats = state.cats!!,
                modifier = modifier,
                onCatClicked = viewModel::onCatClicked,
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
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.padding(marginDefault)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            CatAnimationLottie(
                animation = uiR.raw.cat_playing
            )
        }
        Text(
            text = stringResource(R.string.empty_message),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
    }
}