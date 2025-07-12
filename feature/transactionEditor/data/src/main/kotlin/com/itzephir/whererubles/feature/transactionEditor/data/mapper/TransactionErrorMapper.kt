package com.itzephir.whererubles.feature.transactionEditor.data.mapper

import com.itzephir.whererubles.core.network.transaction.TransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.error.CreateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.error.UpdateTransactionError

fun TransactionError.CreateError.map(): CreateTransactionError = when (this) {
    TransactionError.CreateError.WrongData                 ->
        CreateTransactionError.IncorrectData

    TransactionError.CreateError.Unauthorized              ->
        CreateTransactionError.Unauthorized

    TransactionError.CreateError.AccountOrCategoryNotFound ->
        CreateTransactionError.NotFound

    is TransactionError.CreateError.Else                   ->
        CreateTransactionError.Else(cause)
}

fun TransactionError.UpdateByIdError.map(): UpdateTransactionError = when (this) {
    TransactionError.UpdateByIdError.WrongFormat                          ->
        UpdateTransactionError.IncorrectData

    TransactionError.UpdateByIdError.Unauthorized                         ->
        UpdateTransactionError.Unauthorized

    TransactionError.UpdateByIdError.TransactionAccountOrCategoryNotFound ->
        UpdateTransactionError.NotFound

    is TransactionError.UpdateByIdError.Else                              ->
        UpdateTransactionError.Else(cause)
}