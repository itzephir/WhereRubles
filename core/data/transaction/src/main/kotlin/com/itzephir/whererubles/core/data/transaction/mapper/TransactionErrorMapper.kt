package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.error.CreateTransactionError
import com.itzephir.whererubles.core.data.transaction.error.DeleteTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.error.GetTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.error.GetTransactionsByPeriodError
import com.itzephir.whererubles.core.data.transaction.error.UpdateTransactionByIdError
import com.itzephir.whererubles.core.network.transaction.TransactionError

fun TransactionError.CreateError.toCreateTransactionError(): CreateTransactionError = when (this) {
    TransactionError.CreateError.AccountOrCategoryNotFound ->
        CreateTransactionError.AccountOrCategoryNotFound

    is TransactionError.CreateError.Else                   ->
        CreateTransactionError.Else(cause)

    TransactionError.CreateError.Unauthorized              ->
        CreateTransactionError.Unauthorized

    TransactionError.CreateError.WrongData                 ->
        CreateTransactionError.WrongData
}

fun TransactionError.ReadByIdError.toGetTransactionByIdError(): GetTransactionByIdError = when (this) {
    is TransactionError.ReadByIdError.Else                ->
        GetTransactionByIdError.Else(this.cause)

    is TransactionError.ReadByIdError.TransactionNotFound -> GetTransactionByIdError.TransactionNotFound
    is TransactionError.ReadByIdError.Unauthorized        -> GetTransactionByIdError.Unauthorized
    is TransactionError.ReadByIdError.WrongId             -> GetTransactionByIdError.WrongId
}

fun TransactionError.UpdateByIdError.toUpdateTransactionByIdError(): UpdateTransactionByIdError =
    when (this) {
        is TransactionError.UpdateByIdError.Else                                 ->
            UpdateTransactionByIdError.Else(cause)

        is TransactionError.UpdateByIdError.TransactionAccountOrCategoryNotFound ->
            UpdateTransactionByIdError.TransactionAccountOrCategoryNotFound

        is TransactionError.UpdateByIdError.Unauthorized                         ->
            UpdateTransactionByIdError.Unauthorized

        is TransactionError.UpdateByIdError.WrongFormat                          ->
            UpdateTransactionByIdError.WrongFormat
    }

fun TransactionError.DeleteByIdError.toDeleteTransactionByIdError(): DeleteTransactionByIdError =
    when (this) {
        is TransactionError.DeleteByIdError.Else             ->
            DeleteTransactionByIdError.Else(cause)

        TransactionError.DeleteByIdError.TransactionNotFound -> DeleteTransactionByIdError.TransactionNotFound
        TransactionError.DeleteByIdError.Unauthorized        -> DeleteTransactionByIdError.Unauthorized
        TransactionError.DeleteByIdError.WrongId             -> DeleteTransactionByIdError.WrongId
    }

fun TransactionError.ReadByAccountIdAndPeriodError.toGetTransactionsByPeriodError(): GetTransactionsByPeriodError =
    when (this) {
        is TransactionError.ReadByAccountIdAndPeriodError.Else      ->
            GetTransactionsByPeriodError.Else(cause)

        TransactionError.ReadByAccountIdAndPeriodError.Unauthorized -> GetTransactionsByPeriodError.Unauthorized
        TransactionError.ReadByAccountIdAndPeriodError.WrongFormat  -> GetTransactionsByPeriodError.WrongFormat
    }