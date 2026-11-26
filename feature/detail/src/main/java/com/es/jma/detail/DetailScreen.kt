package com.es.jma.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.es.jma.designsystem.components.CatImage
import com.es.jma.designsystem.components.CountryFlag
import com.es.jma.designsystem.components.InfoPill
import com.es.jma.designsystem.components.MeowButton
import com.es.jma.designsystem.components.MeowChips
import com.es.jma.designsystem.theme.ComponentSize
import com.es.jma.designsystem.theme.ComponentSize.bottomBarSize
import com.es.jma.designsystem.theme.ComponentSize.buttonHeight
import com.es.jma.designsystem.theme.Spacing.lg
import com.es.jma.designsystem.theme.Spacing.md
import com.es.jma.designsystem.theme.Spacing.sm
import com.es.jma.designsystem.theme.Spacing.xs
import com.es.jma.designsystem.theme.Spacing.xsPlus
import com.es.jma.designsystem.theme.Spacing.xxs
import com.es.jma.designsystem.theme.TextSize.Normal
import com.es.jma.designsystem.theme.marginOne
import com.es.jma.designsystem.theme.marginZero
import com.es.jma.model.Breed
import com.es.jma.model.UnitSystem
import com.es.jma.model.UnitSystemEnum
import com.es.jma.ui.screens.ErrorScreen
import com.es.jma.ui.screens.LoadingScreen
import com.es.jma.utils.constants.COMMA
import com.es.jma.utils.constants.MODAL_HEIGHT
import com.es.jma.utils.constants.EMPTY_STRING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    idBreed: String,
    imageCat: String,
    unitSystem: UnitSystemEnum = UnitSystemEnum.METRICS,
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(MODAL_HEIGHT)
        ) {
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
                        unitSystem = unitSystem,
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
    unitSystem: UnitSystemEnum = UnitSystemEnum.METRICS,
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
                catImage = catImage ?: EMPTY_STRING,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(ComponentSize.catImage)
                    .clip(
                        RoundedCornerShape(
                            topEnd = sm,
                            topStart = sm,
                            bottomEnd = marginZero,
                            bottomStart = marginZero
                        )
                    ),
                scale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(md))
            Column(modifier = Modifier.padding(horizontal = lg)) {
                data?.let { cat ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = sm)
                    ) {
                        Column {
                            cat.name?.let { name ->
                                Text(
                                    text = name,
                                    style = MaterialTheme.typography.headlineMedium.copy(
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            cat.altName?.let { altName ->
                                Text(
                                    text = stringResource(R.string.text_alt_name, altName),
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(top = xs)
                                )
                            }
                        }
                        Row {
                            CountryFlag(code = cat.codeCountry, size = Normal)
                            Spacer(Modifier.padding(horizontal = xs))
                            cat.origin?.let { Text(text = it) }
                        }
                    }

                    cat.breedGroup?.let { breedGroup ->
                        InfoPill(text = breedGroup)
                    }

                    if (cat.weight != null || cat.height != null) {
                        CatWeightAndHeight(cat.weight, cat.height, unitSystem)
                    }

                    cat.lifeSpan?.let { lifeSpan ->
                        InfoPill(
                            text = stringResource(R.string.text_life_span, lifeSpan),
                            modifier = Modifier.padding(vertical = sm)
                        )
                    }

                    cat.description?.let { description ->
                        TextColumn(
                            title = stringResource(R.string.text_description),
                            text = description
                        )
                    }

                    cat.temperament?.let { temperament ->
                        val size = temperament.split(COMMA).size
                        MeowChips(
                            text = temperament,
                            modifier = Modifier.padding(vertical = sm),
                            maxVisible = size
                        )
                    }

                    cat.history?.let { history ->
                        TextColumn(
                            title = stringResource(R.string.text_history),
                            text = history
                        )
                    }
                }
            }
        }

        data?.wikipediaUrl?.let { wiki ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(topStart = md, topEnd = md),
                elevation = CardDefaults.cardElevation(marginZero),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = lg, vertical = lg)
                ) {
                    MeowButton(
                        onClick = { onOpenWiki.invoke(wiki) },
                        label = stringResource(R.string.button_text_go_wiki),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(buttonHeight)
                    )
                }
            }
        }
    }
}

@Composable
fun TextColumn(title: String, text: String) {
    Column(
        modifier = Modifier.padding(vertical = sm)
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(vertical = xxs),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = text,
        )
    }
}

@Composable
fun CatWeightAndHeight(weight: UnitSystem?, height: UnitSystem?, unitSystem: UnitSystemEnum) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = md),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(vertical = md, horizontal = sm),
            verticalAlignment = Alignment.CenterVertically
        ) {
            weight?.let {
                StatColumn(
                    label = stringResource(R.string.text_weight),
                    value = it.textByUnitSystem(unitSystem) ?: stringResource(R.string.value_unknow),
                    subLabel = if (unitSystem == UnitSystemEnum.METRICS) stringResource(R.string.kilograms) else stringResource(
                        R.string.pound
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
            if (weight != null && height != null) {
                VerticalDivider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = xs),
                    thickness = marginOne,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.25f)
                )
            }
            height?.let {
                StatColumn(
                    label = stringResource(R.string.text_height),
                    value = it.textByUnitSystem(unitSystem) ?: stringResource(R.string.value_unknow),
                    subLabel = if (unitSystem == UnitSystemEnum.METRICS) stringResource(R.string.centimeters) else stringResource(
                        R.string.inches
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun StatColumn(
    label: String,
    value: String,
    subLabel: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = xs)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(xsPlus))
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = subLabel,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )
    }
}