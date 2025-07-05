package com.itzephir.whererubles.expenses.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.itzephir.whererubles.expenses.presentation.action.ExpensesAction
import com.itzephir.whererubles.expenses.presentation.intent.ExpensesIntent
import com.itzephir.whererubles.expenses.presentation.mapper.toExpensesState
import com.itzephir.whererubles.expenses.presentation.state.ExpensesState
import com.itzephir.whererubles.expenses.presentation.store.ExpensesStore
import com.itzephir.whererubles.feature.expenses.domain.model.ExpensesToday
import com.itzephir.whererubles.feature.expenses.domain.usecase.GetExpensesTodayUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.dsl.intent
import pro.respawn.flowmvi.dsl.state

/**
 * ViewModel of expenses screen
 */
class ExpensesViewModel(
    savedStateHandle: SavedStateHandle,
    private val getExpensesToday: GetExpensesTodayUseCase,
) : StoreViewModel<ExpensesState, ExpensesIntent, ExpensesAction>(
    ExpensesStore(savedStateHandle) {},
) {
    fun init() = intent { retryInit() }

    fun clear() = viewModelScope.cancel()

    fun retry() = intent {
        val state = state as? ExpensesState.Error ?: return@intent
        viewModelScope.launch {
            when (state) {
                is ExpensesState.Error.Initial -> retryInit()
            }
        }
    }

    private suspend fun PipelineContext<ExpensesState, ExpensesIntent, ExpensesAction>.retryInit() {
        updateState { ExpensesState.Loading }

        val expenses = runCatching {
            withContext(Dispatchers.IO) {
                getExpensesToday().fold(
                    ifLeft = { ExpensesState.Error.Initial },
                    ifRight = ExpensesToday::toExpensesState,
                )
            }
        }.getOrElse {
            coroutineContext.ensureActive()
            Log.e("ExpensesViewModel", "Exception", it)
            ExpensesState.Error.Initial
        }

        updateState { expenses }
    }
}

