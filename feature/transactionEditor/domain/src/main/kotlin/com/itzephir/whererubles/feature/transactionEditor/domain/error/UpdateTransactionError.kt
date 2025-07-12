package com.itzephir.whererubles.feature.transactionEditor.domain.error

sealed interface UpdateTransactionError {
    data object IncorrectData : UpdateTransactionError
    data object Unauthorized : UpdateTransactionError
    data object NotFound : UpdateTransactionError
    data class Else(val cause: Throwable) : UpdateTransactionError
}