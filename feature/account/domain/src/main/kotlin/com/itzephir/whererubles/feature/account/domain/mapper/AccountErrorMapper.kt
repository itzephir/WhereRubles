package com.itzephir.whererubles.feature.account.domain.mapper

import com.itzephir.whererubles.feature.account.domain.error.AccountError

fun AccountError.GetAccountByIdError.toUpdateAccountError(): AccountError.UpdateAccountError =
    when (this) {
        is AccountError.GetAccountByIdError.NotFound     -> AccountError.UpdateAccountError.NotFound
        is AccountError.GetAccountByIdError.Unauthorized -> AccountError.UpdateAccountError.Unauthorized
        is AccountError.GetAccountByIdError.WrongFormat  -> AccountError.UpdateAccountError.WrongFormat
        is AccountError.GetAccountByIdError.Else         -> AccountError.UpdateAccountError.Else(cause)
    }
