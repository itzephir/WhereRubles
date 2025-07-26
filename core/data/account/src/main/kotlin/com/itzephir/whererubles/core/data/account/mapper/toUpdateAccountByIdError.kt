package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.error.UpdateAccountByIdError
import com.itzephir.whererubles.core.network.account.AccountError

internal fun AccountError.UpdateByIdError.toUpdateAccountByIdError(): UpdateAccountByIdError = when (this) {
    is AccountError.UpdateByIdError.Else      -> UpdateAccountByIdError.Else(cause)
    AccountError.UpdateByIdError.NotFound     -> UpdateAccountByIdError.NotFound
    AccountError.UpdateByIdError.Unauthorized -> UpdateAccountByIdError.Unauthorized
    AccountError.UpdateByIdError.WrongData    -> UpdateAccountByIdError.WrongData
}