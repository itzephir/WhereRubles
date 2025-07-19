package com.itzephir.whererubles.core.data.account.error

import com.itzephir.whererubles.core.network.account.AccountError.UpdateByIdError

sealed interface UpdateAccountByIdError {
    data object WrongData : UpdateAccountByIdError

    data object Unauthorized : UpdateAccountByIdError

    data object NotFound : UpdateAccountByIdError

    data class Else(val cause: Throwable) : UpdateAccountByIdError
}