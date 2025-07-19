package com.itzephir.whererubles.core.data.transaction.error

sealed interface UpdateTransactionByIdError {
    data object WrongFormat : UpdateTransactionByIdError
    data object Unauthorized : UpdateTransactionByIdError
    data object TransactionAccountOrCategoryNotFound : UpdateTransactionByIdError
    data class Else(val cause: Throwable) : UpdateTransactionByIdError
}