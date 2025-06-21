package com.itzephir.whererubles.feature.income.presentation.store

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.feature.income.presentation.action.IncomeAction
import com.itzephir.whererubles.feature.income.presentation.intent.IncomeIntent
import com.itzephir.whererubles.feature.income.presentation.state.IncomeState
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.api.Store
import pro.respawn.flowmvi.dsl.reduceLambdas
import pro.respawn.flowmvi.dsl.store
import pro.respawn.flowmvi.plugins.init
import pro.respawn.flowmvi.plugins.recover
import pro.respawn.flowmvi.savedstate.plugins.parcelizeState

typealias IncomeStore = Store<IncomeState, IncomeIntent, IncomeAction>

fun IncomeStore(
    savedStateHandle: SavedStateHandle,
    init: suspend PipelineContext<IncomeState, IncomeIntent, IncomeAction>.() -> Unit = {},
): IncomeStore = store<IncomeState, IncomeIntent, IncomeAction>(initial = IncomeState.Loading) {
    parcelizeState(savedStateHandle)

    recover {
        Log.e("Expenses Store Recover", "Exception", it)
        updateState {
            IncomeState.Error.Initial
        }
        null
    }

    init(init)

    reduceLambdas()
}