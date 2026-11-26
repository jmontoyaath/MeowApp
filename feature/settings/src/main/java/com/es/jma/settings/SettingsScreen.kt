package com.es.jma.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.es.jma.designsystem.theme.Spacing.lg
import com.es.jma.designsystem.theme.Spacing.md
import com.es.jma.model.ThemeConfigEnum
import com.es.jma.model.UnitSystemEnum
import com.es.jma.ui.screens.ErrorScreen
import com.es.jma.ui.screens.LoadingScreen

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    when (state) {
        is SettingsUiState.Loading -> LoadingScreen()
        is SettingsUiState.Error -> ErrorScreen {}
        is SettingsUiState.Success -> {
            val data = (state as SettingsUiState.Success)
            SettingsScreenContent(
                theme = data.theme,
                unitSystem = data.unitSystem,
                onClickTheme = viewModel::updateThemeConfig,
                onClickUnitSystem = viewModel::updateUnitSystem
            )
        }
    }
}

@Composable
fun SettingsScreenContent(
    theme: ThemeConfigEnum,
    unitSystem: UnitSystemEnum,
    modifier: Modifier = Modifier,
    onClickTheme: (theme: ThemeConfigEnum) -> Unit = {},
    onClickUnitSystem: (unitSystem: UnitSystemEnum) -> Unit = {},
) {
    Column(modifier = modifier.padding(all = lg)) {
        Text(
            text = stringResource(R.string.theme),
            style = MaterialTheme.typography.titleMedium
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = md, vertical = md),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow
            )
        ) {
            ThemeConfigEnum.entries.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = theme == option,
                        onClick = { onClickTheme(option) }
                    )
                    Text(text = stringResource(id = option.toLabelRes()))
                }
            }
        }

        Spacer(Modifier.height(lg))

        Text(
            text = stringResource(R.string.unit_system),
            style = MaterialTheme.typography.titleMedium
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = md, vertical = md),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow
            )
        ) {
            UnitSystemEnum.entries.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = unitSystem == option,
                        onClick = { onClickUnitSystem(option) }
                    )
                    Text(text = stringResource(id = option.toLabelRes()))
                }
            }
        }
    }
}

@StringRes
fun ThemeConfigEnum.toLabelRes(): Int = when (this) {
    ThemeConfigEnum.LIGHT -> R.string.theme_light
    ThemeConfigEnum.DARK -> R.string.theme_dark
}

@StringRes
fun UnitSystemEnum.toLabelRes(): Int = when (this) {
    UnitSystemEnum.METRICS -> R.string.unit_metrics
    UnitSystemEnum.IMPERIAL -> R.string.unit_imperial
}