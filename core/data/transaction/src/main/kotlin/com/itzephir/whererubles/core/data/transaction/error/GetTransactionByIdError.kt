package com.itzephir.whererubles.core.data.transaction.error

sealed interface GetTransactionByIdError {
    data object WrongId : GetTransactionByIdError
    data object Unauthorized : GetTransactionByIdError
    data object TransactionNotFound : GetTransactionByIdError
    data class Else(val cause: Throwable) : GetTransactionByIdError
}