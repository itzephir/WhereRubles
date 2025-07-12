package com.itzephir.whererubles.feature.transactionEditor.domain.mapper

import com.itzephir.whererubles.feature.transactionEditor.domain.error.CreateOrUpdateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.error.CreateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.error.UpdateTransactionError

fun CreateTransactionError.map() = when (this) {
    is CreateTransactionError.IncorrectData -> CreateOrUpdateTransactionError.IncorrectData
    is CreateTransactionError.Unauthorized  -> CreateOrUpdateTransactionError.Unauthorized
    is CreateTransactionError.NotFound      -> CreateOrUpdateTransactionError.NotFound
    is CreateTransactionError.Else          -> CreateOrUpdateTransactionError.Else(cause)
}

fun UpdateTransactionError.map() = when(this){
    UpdateTransactionError.IncorrectData -> CreateOrUpdateTransactionError.IncorrectData
    UpdateTransactionError.Unauthorized  -> CreateOrUpdateTransactionError.Unauthorized
    UpdateTransactionError.NotFound      -> CreateOrUpdateTransactionError.NotFound
    is UpdateTransactionError.Else       -> CreateOrUpdateTransactionError.Else(cause)
}
