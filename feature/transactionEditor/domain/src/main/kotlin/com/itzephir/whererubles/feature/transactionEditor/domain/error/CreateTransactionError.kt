package com.itzephir.whererubles.feature.transactionEditor.domain.error

sealed interface CreateTransactionError {
    data object IncorrectData : CreateTransactionError
    data object Unauthorized : CreateTransactionError
    data object NotFound : CreateTransactionError
    data class Else(val cause: Throwable) : CreateTransactionError
}