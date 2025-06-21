package com.itzephir.whererubles.expenses.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.itzephir.whererubles.expenses.presentation.action.ExpensesHistoryAction
import com.itzephir.whererubles.expenses.presentation.intent.ExpensesHistoryIntent
import com.itzephir.whererubles.expenses.presentation.mapper.toExpensesHistory
import com.itzephir.whererubles.expenses.presentation.state.ExpensesHistoryState
import com.itzephir.whererubles.expenses.presentation.store.ExpensesHistoryStore
import com.itzephir.whererubles.feature.expenses.domain.model.ExpensesByPeriod
import com.itzephir.whererubles.feature.expenses.domain.usecase.GetExpensesByPeriodUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.dsl.intent
import pro.respawn.flowmvi.dsl.state

class ExpensesHistoryViewModel(
    savedStateHandle: SavedStateHandle,
    private val getExpensesByPeriod: GetExpensesByPeriodUseCase,
) : StoreViewModel<ExpensesHistoryState, ExpensesHistoryIntent, ExpensesHistoryAction>(
    ExpensesHistoryStore(savedStateHandle) {
        val expensesHistory = withContext(Dispatchers.IO) {
            getExpensesByPeriod(state.start, state.end).fold(
                ifLeft = { ExpensesHistoryState.Error.Initial(state.start, state.end) },
                ifRight = ExpensesByPeriod::toExpensesHistory
            )
        }

        updateState { expensesHistory }
    },
) {
    fun retry() = intent {
        val state = state as? ExpensesHistoryState.Error ?: return@intent
        viewModelScope.launch {
            when(state){
                is ExpensesHistoryState.Error.Initial -> retryInitial()
            }
        }
    }

    suspend fun PipelineContext<ExpensesHistoryState, ExpensesHistoryIntent, ExpensesHistoryAction>.retryInitial(){
        val expensesHistory = withContext(Dispatchers.IO) {
            getExpensesByPeriod(state.start, state.end).fold(
                ifLeft = { ExpensesHistoryState.Error.Initial(state.start, state.end) },
                ifRight = ExpensesByPeriod::toExpensesHistory
            )
        }

        updateState { expensesHistory }
    }
}

