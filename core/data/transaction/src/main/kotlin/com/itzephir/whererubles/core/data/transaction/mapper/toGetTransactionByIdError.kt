package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.error.GetTransactionByIdError
import com.itzephir.whererubles.core.network.transaction.TransactionError

internal fun TransactionError.ReadByIdError.toGetTransactionByIdError(): GetTransactionByIdError =
    when (this) {
        is TransactionError.ReadByIdError.Else             -> GetTransactionByIdError.Else(cause)
        TransactionError.ReadByIdError.TransactionNotFound -> GetTransactionByIdError.TransactionNotFound
        TransactionError.ReadByIdError.Unauthorized        -> GetTransactionByIdError.Unauthorized
        TransactionError.ReadByIdError.WrongId             -> GetTransactionByIdError.WrongId
    }