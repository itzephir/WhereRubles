package com.itzephir.whererubles.feature.account.domain.error

/**
 * Errors while interacting with account
 */
sealed interface AccountError {

    /**
     * Error while getting account
     */
    sealed interface GetAccountError : AccountError {

        /**
         * Unauthorized request
         */
        data object Unauthorized : GetAccountError


        /**
         * Response with empty list
         */
        data object EmptyList : GetAccountError


        /**
         * Any other error
         */
        data class Else(val cause: Throwable) : GetAccountError
    }
}
