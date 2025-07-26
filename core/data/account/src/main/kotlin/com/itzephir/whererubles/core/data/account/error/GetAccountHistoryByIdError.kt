package com.itzephir.whererubles.core.data.account.error

sealed interface GetAccountHistoryByIdError {
    data object WrongId : GetAccountHistoryByIdError

    data object Unauthorized : GetAccountHistoryByIdError

    data object NotFound : GetAccountHistoryByIdError

    data class Else(val cause: Throwable) : GetAccountHistoryByIdError
}