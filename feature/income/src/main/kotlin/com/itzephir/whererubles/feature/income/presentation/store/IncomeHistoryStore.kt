package com.itzephir.whererubles.feature.income.presentation.store

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.feature.income.presentation.action.IncomeHistoryAction
import com.itzephir.whererubles.feature.income.presentation.state.IncomeHistoryState
import com.itzephir.whererubles.feature.income.presentation.intent.IncomeHistoryIntent
import kotlin.time.Clock
import kotlin.time.Instant
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

typealias IncomeHistoryStore = Store<IncomeHistoryState, IncomeHistoryIntent, IncomeHistoryAction>

fun IncomeHistoryStore(
    savedStateHandle: SavedStateHandle,
    init: suspend PipelineContext<IncomeHistoryState, IncomeHistoryIntent, IncomeHistoryAction>.() -> Unit = {},
): IncomeHistoryStore =
    store<IncomeHistoryState, IncomeHistoryIntent, IncomeHistoryAction>(
        initial = IncomeHistoryState.Loading(
            startOfCurrentMonth(),
            Clock.System.now(),
        )
    ) {
        parcelizeState(savedStateHandle)

        recover {
            Log.e("Expenses Store Recover", "Exception", it)
            updateState {
                IncomeHistoryState.Error.Initial(start, end)
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
