package com.itzephir.whererubles.core.data.transaction.error

sealed interface GetTransactionsByAccountAndPeriodError {
    data object WrongFormat : GetTransactionsByAccountAndPeriodError

    data object Unauthorized : GetTransactionsByAccountAndPeriodError

    data class Else(val cause: Throwable) : GetTransactionsByAccountAndPeriodError
}