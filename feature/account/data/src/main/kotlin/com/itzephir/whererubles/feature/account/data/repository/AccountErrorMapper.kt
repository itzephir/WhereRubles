package com.itzephir.whererubles.feature.account.data.repository

import com.itzephir.whererubles.core.network.account.AccountError
import com.itzephir.whererubles.core.network.account.AccountError.ReadAllError
import com.itzephir.whererubles.feature.account.domain.error.AccountError.UpdateAccountError
import com.itzephir.whererubles.feature.account.domain.error.AccountError.GetAccountError

fun ReadAllError.map() = when (this) {
    is ReadAllError.Unauthorized -> GetAccountError.Unauthorized

    is ReadAllError.Else         -> GetAccountError.Else(cause)
}

internal fun AccountError.UpdateByIdError.toUpdateAccountError(): UpdateAccountError =
    when (this) {
        is AccountError.UpdateByIdError.Unauthorized -> UpdateAccountError.Unauthorized
        is AccountError.UpdateByIdError.WrongData    -> UpdateAccountError.WrongFormat
        is AccountError.UpdateByIdError.NotFound     -> UpdateAccountError.NotFound
        is AccountError.UpdateByIdError.Else         -> UpdateAccountError.Else(cause)
    }
