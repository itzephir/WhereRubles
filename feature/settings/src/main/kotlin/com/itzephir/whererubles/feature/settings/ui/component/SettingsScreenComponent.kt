package com.itzephir.whererubles.feature.settings.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.itzephir.whererubles.feature.settings.presentation.viewmodel.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
fun SettingsScreenComponent(
    viewModel: SettingsViewModel = koinViewModel(),
) {
    val state by viewModel.subscribe()

    SettingsScreenLayout(
        state = state,
        onDarkThemeChanged = viewModel::changeDarkTheme,
    )
}