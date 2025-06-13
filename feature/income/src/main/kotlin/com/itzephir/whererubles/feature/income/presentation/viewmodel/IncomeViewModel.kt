package com.itzephir.whererubles.feature.income.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.core.format.mapAmount
import com.itzephir.whererubles.domain.model.AccountId
import com.itzephir.whererubles.domain.usecase.GetIncomeUseCase
import com.itzephir.whererubles.feature.income.presentation.action.IncomeAction
import com.itzephir.whererubles.feature.income.presentation.intent.IncomeIntent
import com.itzephir.whererubles.feature.income.presentation.model.Income
import com.itzephir.whererubles.feature.income.presentation.model.IncomeId
import com.itzephir.whererubles.feature.income.presentation.state.IncomeState
import com.itzephir.whererubles.feature.income.presentation.store.IncomeStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.android.StoreViewModel

class IncomeViewModel(
    savedStateHandle: SavedStateHandle,
    val getIncome: GetIncomeUseCase,
) : StoreViewModel<IncomeState, IncomeIntent, IncomeAction>(
    IncomeStore(savedStateHandle) {
        val (total, income) = withContext(Dispatchers.IO) { getIncome(AccountId(0)) }
        updateState {
            IncomeState.Income(
                total = total.mapAmount(),
                income = income.map { transaction ->
                    Income(
                        id = IncomeId(transaction.id.value),
                        title = transaction.category.name,
                        amount = transaction.amount.mapAmount(transaction.account.currency),
                    )
                },
            )
        }
    },
)