package com.itzephir.whererubles.feature.transactionEditor.data.mapper

import com.itzephir.whererubles.core.data.transaction.error.CreateTransactionError

internal fun CreateTransactionError.toCreateTransactionError(): com.itzephir.whererubles.feature.transactionEditor.domain.error.CreateTransactionError =
    when (this) {
        CreateTransactionError.AccountOrCategoryNotFound -> com.itzephir.whererubles.feature.transactionEditor.domain.error.CreateTransactionError.NotFound
        is CreateTransactionError.Else                   -> com.itzephir.whererubles.feature.transactionEditor.domain.error.CreateTransactionError.Else(cause)
        CreateTransactionError.Unauthorized              -> com.itzephir.whererubles.feature.transactionEditor.domain.error.CreateTransactionError.Unauthorized
        CreateTransactionError.WrongData                 -> com.itzephir.whererubles.feature.transactionEditor.domain.error.CreateTransactionError.IncorrectData
    }