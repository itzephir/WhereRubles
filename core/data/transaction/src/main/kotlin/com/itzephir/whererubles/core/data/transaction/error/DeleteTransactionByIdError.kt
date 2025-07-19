package com.itzephir.whererubles.core.data.transaction.error

import com.itzephir.whererubles.core.network.transaction.TransactionError.DeleteByIdError

sealed interface DeleteTransactionByIdError {
    data object WrongId : DeleteTransactionByIdError

    data object Unauthorized : DeleteTransactionByIdError

    data object TransactionNotFound : DeleteTransactionByIdError

    data class Else(val cause: Throwable) : DeleteTransactionByIdError
}