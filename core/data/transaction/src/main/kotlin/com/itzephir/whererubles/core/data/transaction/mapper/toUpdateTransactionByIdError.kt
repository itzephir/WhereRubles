package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.error.UpdateTransactionByIdError
import com.itzephir.whererubles.core.network.transaction.TransactionError

internal fun TransactionError.UpdateByIdError.toUpdateTransactionByIdError(): UpdateTransactionByIdError =
    when (this) {
        is TransactionError.UpdateByIdError.Else                              ->
            UpdateTransactionByIdError.Else(cause)

        TransactionError.UpdateByIdError.TransactionAccountOrCategoryNotFound ->
            UpdateTransactionByIdError.TransactionAccountOrCategoryNotFound

        TransactionError.UpdateByIdError.Unauthorized                         ->
            UpdateTransactionByIdError.Unauthorized

        TransactionError.UpdateByIdError.WrongFormat                          ->
            UpdateTransactionByIdError.WrongFormat
    }