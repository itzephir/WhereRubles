package com.itzephir.whererubles.feature.settings.di.settings

import com.itzephir.whererubles.feature.settings.presentation.viewmodel.SettingsViewModel
import javax.inject.Inject

class SettingsContext @Inject constructor(
    val viewModelFactory: SettingsViewModel.Factory,
)
