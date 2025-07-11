package com.itzephir.whererubles.feature.income.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.itzephir.whererubles.feature.income.domain.model.IncomeByPeriod
import com.itzephir.whererubles.feature.income.domain.usecase.GetIncomeByPeriodUseCase
import com.itzephir.whererubles.feature.income.presentation.action.IncomeHistoryAction
import com.itzephir.whererubles.feature.income.presentation.intent.IncomeHistoryIntent
import com.itzephir.whererubles.feature.income.presentation.mapper.toIncomeHistory
import com.itzephir.whererubles.feature.income.presentation.state.IncomeHistoryState
import com.itzephir.whererubles.feature.income.presentation.store.IncomeHistoryStore
import kotlinx.coroutines.Dispatchers
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
 * ViewModel for income history screen
 */
class IncomeHistoryViewModel(
    savedStateHandle: SavedStateHandle,
    private val getIncomeByPeriod: GetIncomeByPeriodUseCase,
) : StoreViewModel<IncomeHistoryState, IncomeHistoryIntent, IncomeHistoryAction>(
    IncomeHistoryStore(savedStateHandle) {
        val incomeHistory = withContext(Dispatchers.IO) {
            getIncomeByPeriod(state.start, state.end).fold(
                ifLeft = { IncomeHistoryState.Error.Initial(state.start, state.end) },
                ifRight = IncomeByPeriod::toIncomeHistory
            )
        }

        updateState { incomeHistory }
    },
) {
    fun retry() = intent {
        val state = state as? IncomeHistoryState.Error ?: return@intent
        viewModelScope.launch {
            when (state) {
                is IncomeHistoryState.Error.Initial -> retryInitial()
            }
        }
    }

    suspend fun PipelineContext<IncomeHistoryState, IncomeHistoryIntent, IncomeHistoryAction>.retryInitial() {
        updateState { IncomeHistoryState.Loading(start, end) }

        val incomeHistory = runCatching {
            withContext(Dispatchers.IO) {
                getIncomeByPeriod(state.start, state.end).fold(
                    ifLeft = { IncomeHistoryState.Error.Initial(state.start, state.end) },
                    ifRight = IncomeByPeriod::toIncomeHistory
                )
            }
        }.getOrElse {
            coroutineContext.ensureActive()
            Log.e("IncomeHistoryViewModel", "Exception", it)
            IncomeHistoryState.Error.Initial(state.start, state.end)
        }

        updateState { incomeHistory }
    }

    fun changeStart(start: Long?) = intent {
        if (start == null) return@intent
        val new = Instant.fromEpochMilliseconds(start)
        updateState {
            IncomeHistoryState.Loading(
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
            IncomeHistoryState.Loading(
                start = start,
                end = new,
            )
        }
        viewModelScope.launch {
            retryInitial()
        }
    }

    class Factory @Inject constructor(
        private val getIncomeByPeriodUseCase: GetIncomeByPeriodUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
            val savedStateHandle = extras.createSavedStateHandle()

            @Suppress("UNCHECKED_CAST")
            return IncomeHistoryViewModel(
                savedStateHandle = savedStateHandle,
                getIncomeByPeriod = getIncomeByPeriodUseCase,
            ) as T
        }
    }
}

