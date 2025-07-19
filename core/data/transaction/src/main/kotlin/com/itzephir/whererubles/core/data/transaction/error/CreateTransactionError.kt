package com.itzephir.whererubles.core.data.transaction.error

sealed interface CreateTransactionError {
    data object WrongData : CreateTransactionError
    data object Unauthorized : CreateTransactionError
    data object AccountOrCategoryNotFound : CreateTransactionError
    data class Else(val cause: Throwable) : CreateTransactionError
}