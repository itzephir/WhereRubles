package com.itzephir.whererubles.feature.expenses.domain.error

sealed interface ExpensesByAccountAndPeriodError {
    data object WrongFormat: ExpensesByAccountAndPeriodError

    data object Unauthorized: ExpensesByAccountAndPeriodError

    data class Else(val cause: Throwable): ExpensesByAccountAndPeriodError
}