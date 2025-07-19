package com.itzephir.whererubles.core.data.transaction.error

sealed interface GetTransactionsByPeriodError {
    data object WrongFormat : GetTransactionsByPeriodError

    data object Unauthorized : GetTransactionsByPeriodError

    data class Else(val cause: Throwable) : GetTransactionsByPeriodError
}