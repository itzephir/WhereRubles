package com.itzephir.whererubles.feature.income.domain.error

sealed interface IncomeByPeriodError {
    data object WrongFormat: IncomeByPeriodError

    data object Unauthorized: IncomeByPeriodError

    data object NoAccount: IncomeByPeriodError

    data class Else(val cause: Throwable): IncomeByPeriodError
}