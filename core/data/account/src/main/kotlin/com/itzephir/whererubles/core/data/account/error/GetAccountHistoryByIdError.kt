package com.itzephir.whererubles.core.data.account.error

import com.itzephir.whererubles.core.network.account.AccountError.ReadAccountHistoryByIdError

sealed interface GetAccountHistoryByIdError {
    data object NoInternet: GetAccountHistoryByIdError

    data object WrongId : GetAccountHistoryByIdError

    data object Unauthorized : GetAccountHistoryByIdError

    data object NotFound : GetAccountHistoryByIdError

    data class Else(val cause: Throwable) : GetAccountHistoryByIdError
}