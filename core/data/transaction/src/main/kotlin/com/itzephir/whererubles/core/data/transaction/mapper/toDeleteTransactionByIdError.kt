package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.error.DeleteTransactionByIdError
import com.itzephir.whererubles.core.network.transaction.TransactionError

internal fun TransactionError.DeleteByIdError.toDeleteTransactionByIdError(): DeleteTransactionByIdError =
    when (this) {
        is TransactionError.DeleteByIdError.Else             ->
            DeleteTransactionByIdError.Else(cause)

        TransactionError.DeleteByIdError.TransactionNotFound ->
            DeleteTransactionByIdError.TransactionNotFound

        TransactionError.DeleteByIdError.Unauthorized        ->
            DeleteTransactionByIdError.Unauthorized

        TransactionError.DeleteByIdError.WrongId             ->
            DeleteTransactionByIdError.WrongId
    }