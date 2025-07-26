package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.error.DeleteAccountByIdError
import com.itzephir.whererubles.core.network.account.AccountError

internal fun AccountError.DeleteByIdError.toDeleteAccountByIdError(): DeleteAccountByIdError = when (this) {
    is AccountError.DeleteByIdError.Else      -> DeleteAccountByIdError.Else(cause)
    AccountError.DeleteByIdError.NotCleared   -> DeleteAccountByIdError.NotCleared
    AccountError.DeleteByIdError.NotFound     -> DeleteAccountByIdError.NotFound
    AccountError.DeleteByIdError.Unauthorized -> DeleteAccountByIdError.Unauthorized
    AccountError.DeleteByIdError.WrongData    -> DeleteAccountByIdError.WrongData
}