package com.itzephir.whererubles.feature.income.domain.error

/**
 * Possible error while interacting with income with given period
 */
sealed interface IncomeByPeriodError {

    /**
     * Date was sent in wrong format
     */
    data object WrongFormat: IncomeByPeriodError

    /**
     * Unauthorized request
     */
    data object Unauthorized: IncomeByPeriodError

    /**
     * No account was provided
     */
    data object NoAccount: IncomeByPeriodError

    /**
     * Any other error
     */
    data class Else(val cause: Throwable): IncomeByPeriodError
}
