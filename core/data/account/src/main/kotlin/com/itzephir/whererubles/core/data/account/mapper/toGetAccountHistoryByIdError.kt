package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.error.GetAccountHistoryByIdError
import com.itzephir.whererubles.core.network.account.AccountError

internal fun AccountError.ReadAccountHistoryByIdError.toGetAccountHistoryByIdError(): GetAccountHistoryByIdError =
    when (this) {
        is AccountError.ReadAccountHistoryByIdError.Else      ->
            GetAccountHistoryByIdError.Else(cause)

        AccountError.ReadAccountHistoryByIdError.NotFound     ->
            GetAccountHistoryByIdError.NotFound

        AccountError.ReadAccountHistoryByIdError.Unauthorized ->
            GetAccountHistoryByIdError.Unauthorized

        AccountError.ReadAccountHistoryByIdError.WrongId      ->
            GetAccountHistoryByIdError.WrongId
    }