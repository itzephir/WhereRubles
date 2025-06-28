package com.itzephir.whererubles.feature.expenses.domain.error

/**
 * Possible errors while interacting with expenses by today date
 */
sealed interface ExpensesTodayError {

    /**
     * Account or date was sent with wrong format
     */
    data object WrongFormat: ExpensesTodayError

    /**
     * Unauthorized request
     */
    data object Unauthorized: ExpensesTodayError

    /**
     * No account was provided
     */
    data object NoAccount: ExpensesTodayError

    /**
     * Any other error
     */
    data class Else(val cause: Throwable): ExpensesTodayError
}
