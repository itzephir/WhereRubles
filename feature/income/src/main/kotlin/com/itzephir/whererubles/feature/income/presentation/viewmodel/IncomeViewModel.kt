package com.itzephir.whererubles.feature.income.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.itzephir.whererubles.feature.income.domain.model.IncomeToday
import com.itzephir.whererubles.feature.income.domain.usecase.GetIncomeTodayUseCase
import com.itzephir.whererubles.feature.income.presentation.action.IncomeAction
import com.itzephir.whererubles.feature.income.presentation.intent.IncomeIntent
import com.itzephir.whererubles.feature.income.presentation.mapper.toIncomeState
import com.itzephir.whererubles.feature.income.presentation.state.IncomeState
import com.itzephir.whererubles.feature.income.presentation.store.IncomeStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.dsl.intent
import pro.respawn.flowmvi.dsl.state
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * ViewModel for income screen
 */
class IncomeViewModel(
    savedStateHandle: SavedStateHandle,
    val getIncomeToday: GetIncomeTodayUseCase,
) : StoreViewModel<IncomeState, IncomeIntent, IncomeAction>(
    IncomeStore(savedStateHandle) {
        val incomeHistory = withContext(Dispatchers.IO) {
            getIncomeToday().fold(
                ifLeft = { IncomeState.Error.Initial },
                ifRight = IncomeToday::toIncomeState
            )
        }

        updateState { incomeHistory }
    },
) {
    fun retry() = intent {
        val state = state as? IncomeState.Error ?: return@intent
        viewModelScope.launch {
            when (state) {
                is IncomeState.Error.Initial -> retryInit()
            }
        }
    }

    suspend fun PipelineContext<IncomeState, IncomeIntent, IncomeAction>.retryInit() {
        updateState { IncomeState.Loading }

        val income = runCatching {
            withContext(Dispatchers.IO) {
                getIncomeToday().fold(
                    ifLeft = { IncomeState.Error.Initial },
                    ifRight = IncomeToday::toIncomeState
                )
            }
        }.getOrElse {
            coroutineContext.ensureActive()
            Log.e("IncomeViewModel", "Exception", it)
            IncomeState.Error.Initial
        }

        updateState { income }
    }

    class Factory
    @Inject constructor(private val getIncomeTodayUseCase: GetIncomeTodayUseCase) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
            val savedStateHandle = extras.createSavedStateHandle()

            @Suppress("UNCHECKED_CAST")
            return IncomeViewModel(
                savedStateHandle = savedStateHandle,
                getIncomeToday = getIncomeTodayUseCase,
            ) as T
        }
    }
}
