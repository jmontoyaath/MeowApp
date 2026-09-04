package com.es.jma.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.es.jma.designsystem.animations.LoadingDots
import com.es.jma.designsystem.icon.MeowIcons
import com.es.jma.designsystem.theme.marginDefault
import com.es.jma.ui.views.BreedInformation

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    goBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = modifier.fillMaxSize()) {
        OutlinedTextField(
            value = state.query,
            onValueChange = viewModel::onQueryChanged,
            placeholder = { Text("Buscar raza...") },
            leadingIcon = { Icon(MeowIcons.Search, contentDescription = null) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(marginDefault)
        )

        when {
            state.isLoading -> CenteredMessage { LoadingDots() }
            state.error != null -> CenteredMessage { Text(state.error ?: "Error al buscar") }
            state.query.isBlank() -> CenteredMessage { Text("Escribe el nombre de una raza") }
            state.breeds.isEmpty() -> CenteredMessage { Text("Sin resultados") }
            else -> LazyColumn(
                contentPadding = PaddingValues(marginDefault),
                verticalArrangement = Arrangement.spacedBy(marginDefault)
            ) {
                items(state.breeds, key = { it.id }) { breed ->
                    BreedInformation(
                        breed = breed,
                        isExpanded = state.expandedBreedId == breed.id,
                        images = state.imagesByBreed[breed.id].orEmpty(),
                        isLoadingImages = breed.id in state.isLoadingImage,
                        onClick = { viewModel.onBreedTapped(breed.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CenteredMessage(content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { content() }
}