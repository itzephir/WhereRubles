package com.itzephir.whererubles.feature.account.data.repository

import com.itzephir.whererubles.core.network.account.AccountError
import com.itzephir.whererubles.core.network.account.AccountError.ReadAllError
import com.itzephir.whererubles.feature.account.domain.error.AccountError.GetAccountError

fun ReadAllError.map() = when (this) {
    is ReadAllError.Unauthorized -> GetAccountError.Unauthorized

    is ReadAllError.Else         -> GetAccountError.Else(cause)
}

internal fun AccountError.UpdateByIdError.toUpdateAccountError(): com.itzephir.whererubles.feature.account.domain.error.AccountError.UpdateAccountError =
    when (this) {
        is AccountError.UpdateByIdError.Unauthorized -> com.itzephir.whererubles.feature.account.domain.error.AccountError.UpdateAccountError.Unauthorized
        is AccountError.UpdateByIdError.WrongData    -> com.itzephir.whererubles.feature.account.domain.error.AccountError.UpdateAccountError.WrongFormat
        is AccountError.UpdateByIdError.NotFound     -> com.itzephir.whererubles.feature.account.domain.error.AccountError.UpdateAccountError.NotFound
        is AccountError.UpdateByIdError.Else         -> com.itzephir.whererubles.feature.account.domain.error.AccountError.UpdateAccountError.Else(
            this.cause
        )
    }