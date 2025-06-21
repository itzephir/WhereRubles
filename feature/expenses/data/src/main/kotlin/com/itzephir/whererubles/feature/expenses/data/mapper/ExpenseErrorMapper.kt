package com.itzephir.whererubles.feature.expenses.data.mapper

import com.itzephir.whererubles.core.network.transaction.TransactionError
import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesByAccountAndPeriodError

internal fun TransactionError.ReadByAccountIdAndPeriodError.map(): ExpensesByAccountAndPeriodError =
    when (this) {
        is TransactionError.ReadByAccountIdAndPeriodError.WrongFormat  ->
            ExpensesByAccountAndPeriodError.WrongFormat

        is TransactionError.ReadByAccountIdAndPeriodError.Unauthorized ->
            ExpensesByAccountAndPeriodError.Unauthorized

        is TransactionError.ReadByAccountIdAndPeriodError.Else         ->
            ExpensesByAccountAndPeriodError.Else(cause)
    }