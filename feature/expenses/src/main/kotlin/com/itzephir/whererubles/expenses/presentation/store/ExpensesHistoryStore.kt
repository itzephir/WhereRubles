package com.itzephir.whererubles.expenses.presentation.store

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.expenses.presentation.action.ExpensesHistoryAction
import com.itzephir.whererubles.expenses.presentation.intent.ExpensesHistoryIntent
import com.itzephir.whererubles.expenses.presentation.state.ExpensesHistoryState
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.api.Store
import pro.respawn.flowmvi.dsl.reduceLambdas
import pro.respawn.flowmvi.dsl.store
import pro.respawn.flowmvi.plugins.init
import pro.respawn.flowmvi.plugins.recover
import pro.respawn.flowmvi.savedstate.plugins.parcelizeState

typealias ExpensesHistoryStore = Store<ExpensesHistoryState, ExpensesHistoryIntent, ExpensesHistoryAction>

fun ExpensesHistoryStore(
    savedStateHandle: SavedStateHandle,
    init: suspend PipelineContext<ExpensesHistoryState, ExpensesHistoryIntent, ExpensesHistoryAction>.() -> Unit = {},
): ExpensesHistoryStore =
    store<ExpensesHistoryState, ExpensesHistoryIntent, ExpensesHistoryAction>(
        initial = ExpensesHistoryState.Loading(
            startOfCurrentMonth(),
            Clock.System.now(),
        )
    ) {
        parcelizeState(savedStateHandle)

        recover {
            Log.e("Expenses Store Recover", "Exception", it)
            updateState {
                ExpensesHistoryState.Error.Initial(start, end)
            }
            null
        }

        init(init)

        reduceLambdas()
    }

private fun startOfCurrentMonth(): Instant {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val startOfTheMonth = LocalDateTime(now.year, now.month, 1, 0, 0)
    return startOfTheMonth.toInstant(TimeZone.currentSystemDefault())
}
