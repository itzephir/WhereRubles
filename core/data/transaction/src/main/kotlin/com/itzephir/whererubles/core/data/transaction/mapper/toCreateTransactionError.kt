package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.error.CreateTransactionError
import com.itzephir.whererubles.core.network.transaction.TransactionError

internal fun TransactionError.CreateError.toCreateTransactionError(): CreateTransactionError =
    when (this) {
        TransactionError.CreateError.AccountOrCategoryNotFound ->
            CreateTransactionError.AccountOrCategoryNotFound

        is TransactionError.CreateError.Else                   ->
            CreateTransactionError.Else(cause)

        TransactionError.CreateError.Unauthorized              ->
            CreateTransactionError.Unauthorized

        TransactionError.CreateError.WrongData                 ->
            CreateTransactionError.WrongData
    }