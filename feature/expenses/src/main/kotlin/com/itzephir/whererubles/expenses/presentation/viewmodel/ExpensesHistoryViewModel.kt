package com.itzephir.whererubles.expenses.presentation.viewmodel

import android.R.attr.end
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itzephir.whererubles.expenses.presentation.action.ExpensesHistoryAction
import com.itzephir.whererubles.expenses.presentation.intent.ExpensesHistoryIntent
import com.itzephir.whererubles.expenses.presentation.mapper.toExpensesHistory
import com.itzephir.whererubles.expenses.presentation.state.ExpensesHistoryState
import com.itzephir.whererubles.expenses.presentation.store.ExpensesHistoryStore
import com.itzephir.whererubles.feature.expenses.domain.model.ExpensesByPeriod
import com.itzephir.whererubles.feature.expenses.domain.usecase.GetExpensesByPeriodUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Instant
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.dsl.intent
import pro.respawn.flowmvi.dsl.state
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * ViewModel of expenses history screen
 */
class ExpensesHistoryViewModel(
    savedStateHandle: SavedStateHandle,
    private val getExpensesByPeriod: GetExpensesByPeriodUseCase,
) : StoreViewModel<ExpensesHistoryState, ExpensesHistoryIntent, ExpensesHistoryAction>(
    ExpensesHistoryStore(savedStateHandle) {},
) {
    fun init() = intent { retryInitial() }

    fun clear() = viewModelScope.cancel()

    fun retry() = intent {
        val state = state as? ExpensesHistoryState.Error ?: return@intent
        viewModelScope.launch {
            when (state) {
                is ExpensesHistoryState.Error.Initial -> retryInitial()
            }
        }
    }

    suspend fun PipelineContext<ExpensesHistoryState, ExpensesHistoryIntent, ExpensesHistoryAction>.retryInitial() {
        updateState { ExpensesHistoryState.Loading(start, end) }

        val expensesHistory = runCatching {
            withContext(Dispatchers.IO) {
                getExpensesByPeriod(state.start, state.end).fold(
                    ifLeft = { ExpensesHistoryState.Error.Initial(state.start, state.end) },
                    ifRight = ExpensesByPeriod::toExpensesHistory
                )
            }
        }.getOrElse {
            coroutineContext.ensureActive()
            Log.e("ExpensesHistoryViewModel", "Exception", it)
            ExpensesHistoryState.Error.Initial(state.start, state.end)
        }

        updateState { expensesHistory }
    }

    fun changeStart(start: Long?) = intent {
        if (start == null) return@intent
        val new = Instant.fromEpochMilliseconds(start)
        updateState {
            ExpensesHistoryState.Loading(
                start = new,
                end = end,
            )
        }
        viewModelScope.launch {
            retryInitial()
        }
    }

    fun changeEnd(end: Long?) = intent {
        if (end == null) return@intent
        val new = Instant.fromEpochMilliseconds(end)
        updateState {
            ExpensesHistoryState.Loading(
                start = start,
                end = new,
            )
        }
        viewModelScope.launch {
            retryInitial()
        }
    }

    class Factory @Inject constructor(private val getExpensesByPeriodUseCase: GetExpensesByPeriodUseCase) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
            val savedStateHandle = extras.createSavedStateHandle()

            @Suppress("UNCHECKED_CAST")
            return ExpensesHistoryViewModel(
                savedStateHandle = savedStateHandle,
                getExpensesByPeriod = getExpensesByPeriodUseCase,
            ) as T
        }
    }
}

