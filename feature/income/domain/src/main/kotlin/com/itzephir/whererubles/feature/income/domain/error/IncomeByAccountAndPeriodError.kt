package com.itzephir.whererubles.feature.income.domain.error

sealed interface IncomeByAccountAndPeriodError {
    data object WrongFormat: IncomeByAccountAndPeriodError

    data object Unauthorized: IncomeByAccountAndPeriodError

    data class Else(val cause: Throwable): IncomeByAccountAndPeriodError
}