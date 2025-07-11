package com.itzephir.whererubles.feature.settings.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.itzephir.whererubles.feature.settings.presentation.viewmodel.SettingsViewModel
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
fun SettingsScreenComponent(
    viewModel: SettingsViewModel,
) {
    val state by viewModel.subscribe()

    SettingsScreenLayout(
        state = state,
        onDarkThemeChanged = viewModel::changeDarkTheme,
    )
}
