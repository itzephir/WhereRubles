package com.itzephir.whererubles.feature.expenses.domain.error

/**
 * Possible errors while interacting with expenses with account and period
 */
sealed interface ExpensesByAccountAndPeriodError {

    /**
     * Period or account was sent with wrong format
     */
    data object WrongFormat: ExpensesByAccountAndPeriodError

    /**
     * Unauthorized request
     */
    data object Unauthorized: ExpensesByAccountAndPeriodError

    /**
     * Any other error
     */
    data class Else(val cause: Throwable): ExpensesByAccountAndPeriodError
}
