package com.itzephir.whererubles.feature.income.domain.mapper

import com.itzephir.whererubles.feature.income.domain.error.IncomeByAccountAndPeriodError
import com.itzephir.whererubles.feature.income.domain.error.IncomeByPeriodError
import com.itzephir.whererubles.feature.income.domain.error.IncomeTodayError

internal fun IncomeByAccountAndPeriodError.toExpensesTodayError(): IncomeTodayError =
    when (this) {
        is IncomeByAccountAndPeriodError.Unauthorized -> IncomeTodayError.Unauthorized
        is IncomeByAccountAndPeriodError.WrongFormat  -> IncomeTodayError.WrongFormat
        is IncomeByAccountAndPeriodError.Else         -> IncomeTodayError.Else(cause)
    }

internal fun IncomeByAccountAndPeriodError.toExpensesByPeriodError(): IncomeByPeriodError =
    when (this) {
        is IncomeByAccountAndPeriodError.Unauthorized -> IncomeByPeriodError.Unauthorized
        is IncomeByAccountAndPeriodError.WrongFormat  -> IncomeByPeriodError.WrongFormat
        is IncomeByAccountAndPeriodError.Else         -> IncomeByPeriodError.Else(cause)
    }