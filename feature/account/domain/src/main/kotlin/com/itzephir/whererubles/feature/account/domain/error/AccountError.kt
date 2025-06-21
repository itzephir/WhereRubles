package com.itzephir.whererubles.feature.account.domain.error


sealed interface AccountError {
    sealed interface GetAccountError : AccountError {
        data object Unauthorized : GetAccountError

        data object EmptyList : GetAccountError

        data class Else(val cause: Throwable) : GetAccountError
    }
}