package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.error.GetTransactionsByAccountAndPeriodError
import com.itzephir.whererubles.core.network.transaction.TransactionError

internal fun TransactionError.ReadByAccountIdAndPeriodError.toGetTransactionsByAccountAndPeriodError()
        : GetTransactionsByAccountAndPeriodError =
    when (this) {
        is TransactionError.ReadByAccountIdAndPeriodError.Else      ->
            GetTransactionsByAccountAndPeriodError.Else(cause)

        TransactionError.ReadByAccountIdAndPeriodError.Unauthorized ->
            GetTransactionsByAccountAndPeriodError.Unauthorized

        TransactionError.ReadByAccountIdAndPeriodError.WrongFormat  ->
            GetTransactionsByAccountAndPeriodError.WrongFormat
    }