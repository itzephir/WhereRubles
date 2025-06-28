package com.itzephir.whererubles.feature.income.domain.error

/**
 * Possible errors while interacting with income with given account and period
 */
sealed interface IncomeByAccountAndPeriodError {

    /**
     * Date or account was sent in wrong format
     */
    data object WrongFormat: IncomeByAccountAndPeriodError

    /**
     * Unauthorized request
     */
    data object Unauthorized: IncomeByAccountAndPeriodError

    /**
     * Any other error
     */
    data class Else(val cause: Throwable): IncomeByAccountAndPeriodError
}
