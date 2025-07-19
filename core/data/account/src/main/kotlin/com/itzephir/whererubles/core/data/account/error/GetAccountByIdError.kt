package com.itzephir.whererubles.core.data.account.error

import com.itzephir.whererubles.core.network.account.AccountError.ReadByIdError

sealed interface GetAccountByIdError {
    data object NoInternet : GetAccountByIdError

    data object WrongId : GetAccountByIdError

    data object Unauthorized : GetAccountByIdError

    data object NotFound : GetAccountByIdError

    data class Else(val cause: Throwable) : GetAccountByIdError
}