package com.es.jma.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.es.jma.designsystem.components.CatImage
import com.es.jma.designsystem.theme.marginSmall
import com.es.jma.model.Breed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    idBreed: String,
    imageCat: String,
    viewModel: DetailViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    onOpenWiki: (url: String) -> Unit = {},
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(idBreed) {
        viewModel.getCatDetail(idBreed)
    }

    LaunchedEffect(viewModel) {
        viewModel.action.collect { action ->
            when (action) {
                else -> Unit
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        when (state) {
            is DetailUiState.Loading -> DetailScreenLoading()
            is DetailUiState.Error -> DetailScreenError()
            is DetailUiState.Success -> {
                val data = (state as DetailUiState.Success)
                DetailScreenContent(data = data.data, catImage = imageCat, onOpenWiki = onOpenWiki)
            }
        }
    }
}

@Composable
fun DetailScreenContent(data: Breed?, catImage: String?, onOpenWiki: (url: String) -> Unit = {}) {
    Column {
        CatImage(catImage = catImage ?: "")
        Spacer(modifier = Modifier.padding(marginSmall))
        Text(text = "Cat cat cat cat ${data?.name}")
        data?.wikipediaUrl?.let { wikiLink ->
            Button(onClick = { onOpenWiki.invoke(wikiLink) }) {
                Text(text = "Go kitten info")
            }
        }
    }
}

@Composable
fun DetailScreenError() {
    Text(text = "There is no cat in the bag")
}

@Composable
fun DetailScreenLoading() {
    Text(text = "Searching for cats")
}