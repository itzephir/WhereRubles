package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.error.CreateAccountError
import com.itzephir.whererubles.core.network.account.AccountError

internal fun AccountError.CreateError.toCreateAccountError(): CreateAccountError = when (this) {
    is AccountError.CreateError.Else      -> CreateAccountError.Else(cause)
    AccountError.CreateError.Unauthorized -> CreateAccountError.Unauthorized
    AccountError.CreateError.WrongData    -> CreateAccountError.WrongData
}