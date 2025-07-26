package com.itzephir.whererubles.feature.transactionEditor.data.mapper

import com.itzephir.whererubles.core.data.transaction.error.UpdateTransactionByIdError
import com.itzephir.whererubles.feature.transactionEditor.domain.error.UpdateTransactionError

internal fun UpdateTransactionByIdError.toUpdateTransactionError(): UpdateTransactionError =
    when (this) {
        is UpdateTransactionByIdError.Else                              ->
            UpdateTransactionError.Else(cause)

        UpdateTransactionByIdError.TransactionAccountOrCategoryNotFound ->
            UpdateTransactionError.NotFound

        UpdateTransactionByIdError.Unauthorized                         ->
            UpdateTransactionError.Unauthorized

        UpdateTransactionByIdError.WrongFormat                          ->
            UpdateTransactionError.IncorrectData
    }