package com.itzephir.whererubles.feature.expenses.domain.error

sealed interface ExpensesByPeriodError {
    data object WrongFormat: ExpensesByPeriodError

    data object Unauthorized: ExpensesByPeriodError

    data object NoAccount: ExpensesByPeriodError

    data class Else(val cause: Throwable): ExpensesByPeriodError
}