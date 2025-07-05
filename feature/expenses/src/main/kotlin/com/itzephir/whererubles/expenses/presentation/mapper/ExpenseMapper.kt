package com.itzephir.whererubles.expenses.presentation.mapper

import com.itzephir.whererubles.core.format.formatAmount
import com.itzephir.whererubles.expenses.presentation.model.ExpenseId
import com.itzephir.whererubles.expenses.presentation.state.ExpensesHistoryState
import com.itzephir.whererubles.expenses.presentation.state.ExpensesState
import com.itzephir.whererubles.feature.expenses.domain.model.Expense
import com.itzephir.whererubles.feature.expenses.domain.model.ExpensesByPeriod
import com.itzephir.whererubles.feature.expenses.domain.model.ExpensesToday

internal fun Expense.map(): com.itzephir.whererubles.expenses.presentation.model.Expense =
    com.itzephir.whererubles.expenses.presentation.model.Expense(
        id = ExpenseId(id.value),
        icon = emoji,
        title = title,
        amount = amount.formatAmount(currency),
        time = transactionDate,
        comment = comment,
    )

internal fun ExpensesToday.toExpensesState(): ExpensesState.Expenses = ExpensesState.Expenses(
    total = total.formatAmount(currency),
    expenses = expenses.map(Expense::map),
)

internal fun ExpensesByPeriod.toExpensesHistory(): ExpensesHistoryState.ExpensesHistory =
    ExpensesHistoryState.ExpensesHistory(
        total = total.formatAmount(currency),
        start = start,
        end = end,
        expenses = expenses.map { it.map() }
    )
