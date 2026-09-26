package com.es.jma.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.es.jma.designsystem.components.CatImage
import com.es.jma.designsystem.components.CountryFlag
import com.es.jma.designsystem.components.MeowButton
import com.es.jma.designsystem.components.MeowChips
import com.es.jma.designsystem.theme.bottomBarSize
import com.es.jma.designsystem.theme.imageModalCat
import com.es.jma.designsystem.theme.marginBig
import com.es.jma.designsystem.theme.marginDefault
import com.es.jma.designsystem.theme.marginMedium
import com.es.jma.designsystem.theme.marginSmaller
import com.es.jma.designsystem.theme.marginTiny
import com.es.jma.designsystem.theme.marginZero
import com.es.jma.designsystem.theme.textNormal
import com.es.jma.model.Breed
import com.es.jma.ui.screens.ErrorScreen
import com.es.jma.ui.screens.LoadingScreen

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
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

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
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.90f)) {
            when (state) {
                is DetailUiState.Loading -> LoadingScreen()
                is DetailUiState.Error -> ErrorScreen {
                    onDismiss.invoke()
                }

                is DetailUiState.Success -> {
                    val data = (state as DetailUiState.Success)
                    DetailScreenContent(
                        data = data.data,
                        catImage = imageCat,
                        onOpenWiki = onOpenWiki
                    )
                }
            }
        }
    }
}

@Composable
fun DetailScreenContent(
    data: Breed?,
    catImage: String?,
    onOpenWiki: (url: String) -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = bottomBarSize)
        ) {
            CatImage(
                catImage = catImage ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(imageModalCat)
                    .clip(
                        RoundedCornerShape(
                            topEnd = marginSmaller,
                            topStart = marginSmaller,
                            bottomEnd = marginZero,
                            bottomStart = marginZero
                        )
                    ),
                scale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(marginMedium))
            Column(modifier = Modifier.padding(horizontal = marginDefault)) {
                data?.let { cat ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(vertical = marginSmaller)
                    ) {
                        cat.name?.let { Text(text = it) }
                        Row {
                            CountryFlag(code = cat.codeCountry, size = textNormal)
                            Spacer(Modifier.padding(horizontal = marginTiny))
                            cat.origin?.let { Text(text = it) }
                        }
                    }
                    cat.description?.let { Text(text = it, modifier = Modifier.padding(vertical = marginSmaller)) }
                    cat.history?.let { history ->
                        Text(text = history)
                    }
                    cat.temperament?.let { MeowChips(text = it, modifier = Modifier.padding(vertical = marginSmaller)) }
                    cat.lifeSpan?.let {
                        Text(text = stringResource(R.string.life_spam, it), modifier = Modifier.padding(vertical = marginSmaller))
                    }
                }
            }
        }

        data?.wikipediaUrl?.let { wiki ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(topStart = marginMedium, topEnd = marginMedium),
                elevation = CardDefaults.cardElevation(marginZero),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = marginDefault, vertical = marginDefault)
                ) {
                    MeowButton(
                        onClick = { onOpenWiki.invoke(wiki) },
                        label = stringResource(R.string.go_wiki),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(marginBig)
                    )
                }
            }
        }
    }
}