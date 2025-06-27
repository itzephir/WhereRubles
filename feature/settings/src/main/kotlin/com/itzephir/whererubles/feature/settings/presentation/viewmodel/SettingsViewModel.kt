package com.itzephir.whererubles.feature.settings.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.feature.settings.presentation.action.SettingsAction
import com.itzephir.whererubles.feature.settings.presentation.intent.SettingsIntent
import com.itzephir.whererubles.feature.settings.presentation.state.SettingsState
import com.itzephir.whererubles.feature.settings.presentation.store.SettingsStore
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.dsl.intent

class SettingsViewModel(
    savedStateHandle: SavedStateHandle,
) : StoreViewModel<SettingsState, SettingsIntent, SettingsAction>(
    SettingsStore(savedStateHandle)
) {
    fun changeDarkTheme(darkTheme: Boolean) = intent {
        updateState {
            copy(darkTheme = darkTheme)
        }
    }
}
