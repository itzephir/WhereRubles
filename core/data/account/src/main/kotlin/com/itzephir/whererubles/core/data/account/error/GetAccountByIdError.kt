package com.itzephir.whererubles.core.data.account.error

sealed interface GetAccountByIdError {

    data object WrongId : GetAccountByIdError

    data object Unauthorized : GetAccountByIdError

    data object NotFound : GetAccountByIdError

    data class Else(val cause: Throwable) : GetAccountByIdError
}