package com.itzephir.whererubles.feature.income.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.itzephir.whererubles.feature.income.domain.model.IncomeByPeriod
import com.itzephir.whererubles.feature.income.domain.usecase.GetIncomeByPeriodUseCase
import com.itzephir.whererubles.feature.income.presentation.action.IncomeHistoryAction
import com.itzephir.whererubles.feature.income.presentation.intent.IncomeHistoryIntent
import com.itzephir.whererubles.feature.income.presentation.mapper.toIncomeHistory
import com.itzephir.whererubles.feature.income.presentation.state.IncomeHistoryState
import com.itzephir.whererubles.feature.income.presentation.store.IncomeHistoryStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.dsl.intent
import pro.respawn.flowmvi.dsl.state

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
        val incomeHistory = withContext(Dispatchers.IO) {
            getIncomeByPeriod(state.start, state.end).fold(
                ifLeft = { IncomeHistoryState.Error.Initial(state.start, state.end) },
                ifRight = IncomeByPeriod::toIncomeHistory
            )
        }

        updateState { incomeHistory }
    }
}

