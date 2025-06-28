package com.itzephir.whererubles.feature.settings.presentation.store

import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.feature.settings.presentation.action.SettingsAction
import com.itzephir.whererubles.feature.settings.presentation.intent.SettingsIntent
import com.itzephir.whererubles.feature.settings.presentation.state.SettingsState
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.api.Store
import pro.respawn.flowmvi.dsl.reduceLambdas
import pro.respawn.flowmvi.dsl.store
import pro.respawn.flowmvi.plugins.init
import pro.respawn.flowmvi.savedstate.plugins.parcelizeState

typealias SettingsStore = Store<SettingsState, SettingsIntent, SettingsAction>

fun SettingsStore(
    savedStateHandle: SavedStateHandle,
    init: suspend PipelineContext<SettingsState, SettingsIntent, SettingsAction>.() -> Unit = {},
): SettingsStore = store<SettingsState, SettingsIntent, SettingsAction>(initial = SettingsState()) {
    parcelizeState(savedStateHandle)

    init(init)

    reduceLambdas()
}
