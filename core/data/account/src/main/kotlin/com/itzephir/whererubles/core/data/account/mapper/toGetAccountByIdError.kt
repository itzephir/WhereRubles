package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.error.GetAccountByIdError
import com.itzephir.whererubles.core.network.account.AccountError

internal fun AccountError.ReadByIdError.toGetAccountByIdError(): GetAccountByIdError = when (this) {
    is AccountError.ReadByIdError.Else      -> GetAccountByIdError.Else(this.cause)
    AccountError.ReadByIdError.NotFound     -> GetAccountByIdError.NotFound
    AccountError.ReadByIdError.Unauthorized -> GetAccountByIdError.Unauthorized
    AccountError.ReadByIdError.WrongId      -> GetAccountByIdError.NotFound
}