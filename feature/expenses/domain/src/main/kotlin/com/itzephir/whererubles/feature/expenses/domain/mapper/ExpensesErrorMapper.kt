package com.itzephir.whererubles.feature.expenses.domain.mapper

import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesByAccountAndPeriodError
import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesByPeriodError
import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesTodayError

internal fun ExpensesByAccountAndPeriodError.toExpensesTodayError(): ExpensesTodayError =
    when (this) {
        is ExpensesByAccountAndPeriodError.Unauthorized -> ExpensesTodayError.Unauthorized
        is ExpensesByAccountAndPeriodError.WrongFormat  -> ExpensesTodayError.WrongFormat
        is ExpensesByAccountAndPeriodError.Else         -> ExpensesTodayError.Else(cause)
    }

internal fun ExpensesByAccountAndPeriodError.toExpensesByPeriodError(): ExpensesByPeriodError =
    when (this) {
        is ExpensesByAccountAndPeriodError.Unauthorized -> ExpensesByPeriodError.Unauthorized
        is ExpensesByAccountAndPeriodError.WrongFormat  -> ExpensesByPeriodError.WrongFormat
        is ExpensesByAccountAndPeriodError.Else         -> ExpensesByPeriodError.Else(cause)
    }