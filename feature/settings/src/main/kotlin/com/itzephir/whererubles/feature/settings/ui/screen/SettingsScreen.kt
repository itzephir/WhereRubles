package com.itzephir.whererubles.feature.settings.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.itzephir.whererubles.feature.settings.di.SettingsContext
import com.itzephir.whererubles.feature.settings.ui.component.SettingsScreenComponent
import org.koin.compose.KoinIsolatedContext

@Composable
fun SettingsScreen() {
    val applicationContext = LocalContext.current.applicationContext

    val settingsContext = remember { SettingsContext(applicationContext) }

    val koinApplication = settingsContext.koinApplication

    KoinIsolatedContext(koinApplication) {
        SettingsScreenComponent()
    }
}