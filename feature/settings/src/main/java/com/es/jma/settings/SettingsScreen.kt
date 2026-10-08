package com.es.jma.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.es.jma.designsystem.theme.marginDefault
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
    Column(modifier = modifier.padding(marginDefault)) {
        Text(text = "Theme", style = MaterialTheme.typography.titleMedium)
        ThemeConfigEnum.entries.forEach { option ->
            Row (verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = theme == option,
                    onClick = { onClickTheme(option) }
                )
                Text(option.name)
            }
        }

        Spacer(Modifier.height(marginDefault))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Unit System")
            Switch(
                checked = unitSystem == UnitSystemEnum.IMPERIAL,
                onCheckedChange = { checked ->
                    onClickUnitSystem(
                        if (checked) UnitSystemEnum.IMPERIAL else UnitSystemEnum.METRICS
                    )
                }
            )
        }
    }
}