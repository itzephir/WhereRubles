package com.itzephir.whererubles.feature.settings.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.itzephir.whererubles.feature.settings.presentation.action.SettingsAction
import com.itzephir.whererubles.feature.settings.presentation.intent.SettingsIntent
import com.itzephir.whererubles.feature.settings.presentation.state.SettingsState
import com.itzephir.whererubles.feature.settings.presentation.store.SettingsStore
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.dsl.intent
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * ViewModel for settings screen
 */
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

    class Factory @Inject constructor() : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
            val savedStateHandle = extras.createSavedStateHandle()

            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(savedStateHandle) as T
        }
    }
}
