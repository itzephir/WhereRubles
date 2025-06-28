package com.itzephir.whererubles.feature.income.domain.error

/**
 * Possible errors while interacting with today income
 */
sealed interface IncomeTodayError {

    /**
     * Date was sent in wrong format
     */
    data object WrongFormat: IncomeTodayError

    /**
     * Unauthorized request
     */
    data object Unauthorized: IncomeTodayError

    /**
     * No account was provided
     */
    data object NoAccount: IncomeTodayError

    /**
     * Any other error
     */
    data class Else(val cause: Throwable): IncomeTodayError
}
