package com.itzephir.whererubles.feature.settings.ui.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itzephir.whererubles.feature.settings.di.DaggerSettingsFeatureComponent
import com.itzephir.whererubles.feature.settings.di.SettingsFeatureDependencies
import com.itzephir.whererubles.feature.settings.di.settings.DaggerSettingsComponent
import com.itzephir.whererubles.feature.settings.presentation.viewmodel.SettingsViewModel
import com.itzephir.whererubles.feature.settings.ui.component.SettingsScreenComponent

@Composable
fun SettingsScreen(settingsFeatureDependencies: SettingsFeatureDependencies) {
    val settingsFeatureComponent =
        DaggerSettingsFeatureComponent.factory().create(settingsFeatureDependencies)

    val settingsFeatureContext = settingsFeatureComponent.settingsFeatureContext

    val settingsComponent = DaggerSettingsComponent.factory().create(settingsFeatureContext)

    val settingsContext = settingsComponent.settingsContext

    val viewModel = viewModel<SettingsViewModel>(factory = settingsContext.viewModelFactory)

    SettingsScreenComponent(viewModel)
}
