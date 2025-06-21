package com.itzephir.whererubles.feature.income.data.mapper

import com.itzephir.whererubles.core.network.transaction.TransactionError
import com.itzephir.whererubles.feature.income.domain.error.IncomeByAccountAndPeriodError

internal fun TransactionError.ReadByAccountIdAndPeriodError.map(): IncomeByAccountAndPeriodError =
    when (this) {
        is TransactionError.ReadByAccountIdAndPeriodError.WrongFormat  ->
            IncomeByAccountAndPeriodError.WrongFormat

        is TransactionError.ReadByAccountIdAndPeriodError.Unauthorized ->
            IncomeByAccountAndPeriodError.Unauthorized

        is TransactionError.ReadByAccountIdAndPeriodError.Else         ->
            IncomeByAccountAndPeriodError.Else(cause)
    }