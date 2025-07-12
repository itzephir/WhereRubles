package com.itzephir.whererubles.feature.transactionEditor.domain.repository

import arrow.core.Either
import com.itzephir.whererubles.feature.transactionEditor.domain.error.CreateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.error.UpdateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.domain.model.TransactionId

interface TransactionRepository {
    suspend fun createTransaction(
        transaction: Transaction,
    ): Either<CreateTransactionError, Unit>

    suspend fun updateTransaction(
        transactionId: TransactionId,
        transaction: Transaction,
    ): Either<UpdateTransactionError, Unit>
}