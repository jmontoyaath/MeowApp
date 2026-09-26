package com.es.jma.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.es.jma.designsystem.icon.MeowIcons
import com.es.jma.designsystem.theme.marginDefault
import com.es.jma.designsystem.theme.marginLarger
import com.es.jma.model.Breed
import com.es.jma.ui.animation.CatAnimationLottie
import com.es.jma.ui.screens.LoadingScreen
import com.es.jma.ui.views.BreedInformation

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val currentQuery = when (val uiState = state) {
        is SearchUiState.Success -> uiState.query
        else -> ""
    }

    Column(modifier = modifier.fillMaxSize()) {
        OutlinedTextField(
            value = currentQuery,
            onValueChange = viewModel::onQueryChanged,
            placeholder = { Text(text = stringResource(R.string.feature_search_placeholder)) },
            leadingIcon = { Icon(imageVector = MeowIcons.Search, contentDescription = null) },
            singleLine = true,
            shape = RoundedCornerShape(size = marginLarger),
            modifier = Modifier
                .fillMaxWidth()
                .padding(marginDefault)
        )

        when (state) {
            is SearchUiState.Initial -> LoadingScreen()
            is SearchUiState.Success -> {
                val data = (state as SearchUiState.Success)
                when {
                    data.breeds.isEmpty() -> SearchEmptyScreen()
                    data.isSearching -> LoadingScreen()
                    else -> SearchScreenContent(
                        breeds = data.breeds,
                        expandedBreedId = data.expandedBreedId,
                        imagesByBreed = data.imagesByBreed,
                        isLoadingImage = data.isLoadingImage,
                        onBreedClick = viewModel::onBreedTapped
                    )
                }
            }
        }
    }
}

@Composable
fun SearchScreenContent(
    breeds: List<Breed>,
    modifier: Modifier = Modifier,
    expandedBreedId: String? = null,
    imagesByBreed: Map<String, List<String>> = emptyMap(),
    isLoadingImage: Set<String> = emptySet(),
    onBreedClick: (String) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = marginDefault)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(marginDefault)
    ) {
        items(breeds, key = { it.id }) { breed ->
            BreedInformation(
                breed = breed,
                isExpanded = expandedBreedId == breed.id,
                images = imagesByBreed[breed.id].orEmpty(),
                isLoadingImages = breed.id in isLoadingImage,
                onClick = { onBreedClick.invoke(breed.id) }
            )
        }
    }
}

@Composable
fun SearchEmptyScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(marginDefault)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            CatAnimationLottie()
        }
        Text(
            text = stringResource(R.string.empty_message),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
    }
}