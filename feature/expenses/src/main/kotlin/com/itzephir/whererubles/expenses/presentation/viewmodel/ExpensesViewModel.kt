package com.itzephir.whererubles.expenses.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
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
import java.util.Locale

class ExpensesViewModel(
    savedStateHandle: SavedStateHandle,
    private val getExpenses: GetExpensesUseCase,
) : StoreViewModel<ExpensesState, ExpensesIntent, ExpensesAction>(ExpensesStore(savedStateHandle) {
    val (total, expenses) = withContext(Dispatchers.IO) { getExpenses(AccountId(0)) }
    updateState {
        ExpensesState.Expenses(
            total = total.mapAmount(),
            expenses = expenses.map {
                Expense(
                    id = ExpenseId(it.id.value),
                    icon = it.category.emoji,
                    title = it.category.name,
                    price = it.amount.mapAmount(it.account.currency),
                    comment = it.comment,
                )
            }
        )
    }
}) {
    companion object {
        private fun String.mapAmount(currency: String = "RUB"): String {
            val currencySign = when (currency) {
                "RUB" -> "₽"
                "USD" -> "$"
                else  -> currency
            }
            val amount = toDouble()
            val formatted = String.format(Locale.US, "%,.2f", amount).replace(",", " ")

            return "$formatted $currencySign"
        }
    }
}