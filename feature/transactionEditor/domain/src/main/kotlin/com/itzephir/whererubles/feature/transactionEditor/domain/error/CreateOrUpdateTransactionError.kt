package com.itzephir.whererubles.feature.transactionEditor.domain.error

sealed interface CreateOrUpdateTransactionError {
    data object IncorrectData: CreateOrUpdateTransactionError
    data object Unauthorized: CreateOrUpdateTransactionError
    data object NotFound: CreateOrUpdateTransactionError
    data class Else(val cause: Throwable): CreateOrUpdateTransactionError
}