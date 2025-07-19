package com.itzephir.whererubles.core.data.account.error

sealed interface DeleteAccountByIdError {
    data object WrongData : DeleteAccountByIdError

    data object Unauthorized : DeleteAccountByIdError

    data object NotFound : DeleteAccountByIdError

    data object NotCleared : DeleteAccountByIdError

    data class Else(val cause: Throwable) : DeleteAccountByIdError
}