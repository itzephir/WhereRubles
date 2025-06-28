package com.itzephir.whererubles.feature.expenses.domain.error

/**
 * Possible errors while interacting with expenses with date period
 */
sealed interface ExpensesByPeriodError {

    /**
     * Date was sent with wrong format
     */
    data object WrongFormat: ExpensesByPeriodError

    /**
     * Unauthorized request
     */
    data object Unauthorized: ExpensesByPeriodError

    /**
     * No account was provided
     */
    data object NoAccount: ExpensesByPeriodError

    /**
     * Any other error
     */
    data class Else(val cause: Throwable): ExpensesByPeriodError
}
