package com.itzephir.whererubles.feature.expenses.domain.error

sealed interface ExpensesTodayError {
    data object WrongFormat: ExpensesTodayError

    data object Unauthorized: ExpensesTodayError

    data object NoAccount: ExpensesTodayError

    data class Else(val cause: Throwable): ExpensesTodayError
}
