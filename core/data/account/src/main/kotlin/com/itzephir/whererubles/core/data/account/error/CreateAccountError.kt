package com.itzephir.whererubles.core.data.account.error

sealed interface CreateAccountError {
    data object Unauthorized : CreateAccountError

    data object WrongData : CreateAccountError

    data class Else(val cause: Throwable) : CreateAccountError
}