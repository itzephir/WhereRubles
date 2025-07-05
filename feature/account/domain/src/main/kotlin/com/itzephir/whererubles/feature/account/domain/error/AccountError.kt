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

    sealed interface GetAccountByIdError : AccountError {
        data object WrongFormat : GetAccountByIdError

        data object Unauthorized : GetAccountByIdError

        data object NotFound : GetAccountByIdError

        data class Else(val cause: Throwable) : GetAccountByIdError
    }

    sealed interface UpdateAccountError : AccountError {
        data object WrongFormat : UpdateAccountError

        data object Unauthorized : UpdateAccountError

        data object NotFound : UpdateAccountError

        data class Else(val cause: Throwable) : UpdateAccountError
    }
}
