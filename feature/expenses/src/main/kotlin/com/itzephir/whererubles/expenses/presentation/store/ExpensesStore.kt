package com.itzephir.whererubles.expenses.presentation.store

import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.expenses.presentation.action.ExpensesAction
import com.itzephir.whererubles.expenses.presentation.intent.ExpensesIntent
import com.itzephir.whererubles.expenses.presentation.state.ExpensesState
import pro.respawn.flowmvi.api.Store
import pro.respawn.flowmvi.dsl.reduceLambdas
import pro.respawn.flowmvi.dsl.store
import pro.respawn.flowmvi.savedstate.plugins.parcelizeState

typealias ExpensesStore = Store<ExpensesState, ExpensesIntent, ExpensesAction>

fun ExpensesStore(savedStateHandle: SavedStateHandle): ExpensesStore =
    store<ExpensesState, ExpensesIntent, ExpensesAction>(initial = ExpensesState.Loading) {
        parcelizeState(savedStateHandle)

        reduceLambdas()
    }