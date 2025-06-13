package com.itzephir.whererubles.expenses.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.core.format.mapAmount
import com.itzephir.whererubles.domain.model.AccountId
import com.itzephir.whererubles.domain.usecase.GetExpensesUseCase
import com.itzephir.whererubles.expenses.presentation.action.ExpensesAction
import com.itzephir.whererubles.expenses.presentation.intent.ExpensesIntent
import com.itzephir.whererubles.expenses.presentation.model.Expense
import com.itzephir.whererubles.expenses.presentation.model.ExpenseId
import com.itzephir.whererubles.expenses.presentation.state.ExpensesState
import com.itzephir.whererubles.expenses.presentation.store.ExpensesStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.android.StoreViewModel

class ExpensesViewModel(
    savedStateHandle: SavedStateHandle,
    private val getExpenses: GetExpensesUseCase,
) : StoreViewModel<ExpensesState, ExpensesIntent, ExpensesAction>(
    ExpensesStore(savedStateHandle) {
        val (total, expenses) = withContext(Dispatchers.IO) { getExpenses(AccountId(0)) }
        updateState {
            ExpensesState.Expenses(
                total = total.mapAmount(),
                expenses = expenses.map {
                    Expense(
                        id = ExpenseId(it.id.value),
                        icon = it.category.emoji,
                        title = it.category.name,
                        amount = it.amount.mapAmount(it.account.currency),
                        comment = it.comment,
                    )
                },
            )
        }
    },
)