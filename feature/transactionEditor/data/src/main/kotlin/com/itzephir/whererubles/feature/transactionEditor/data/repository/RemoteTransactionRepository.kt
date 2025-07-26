package com.itzephir.whererubles.feature.transactionEditor.data.repository

import arrow.core.Either
import com.itzephir.whererubles.core.data.transaction.TransactionInteractor
import com.itzephir.whererubles.core.data.transaction.error.UpdateTransactionByIdError
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.feature.transactionEditor.data.mapper.TransactionToTransactionOperation
import com.itzephir.whererubles.feature.transactionEditor.data.mapper.toCreateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.data.mapper.toUpdateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.error.CreateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.error.UpdateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.domain.model.TransactionId
import com.itzephir.whererubles.feature.transactionEditor.domain.repository.TransactionRepository
import javax.inject.Inject

class RemoteTransactionRepository @Inject constructor(
    private val transactionInteractor: TransactionInteractor,
) : TransactionRepository {
    override suspend fun createTransaction(
        transaction: Transaction,
    ): Either<CreateTransactionError, Unit> =
        transactionInteractor.create(
            transactionOperation = TransactionToTransactionOperation.map(from = transaction)
        ).mapLeft(
            f = com.itzephir.whererubles.core.data.transaction.error.CreateTransactionError::toCreateTransactionError
        ).map { println(it) }

    override suspend fun updateTransaction(
        transactionId: TransactionId,
        transaction: Transaction,
    ): Either<UpdateTransactionError, Unit> =
        transactionInteractor.updateById(
            id = Id(transactionId.value),
            transactionOperation = TransactionToTransactionOperation.map(from = transaction)
        ).mapLeft(UpdateTransactionByIdError::toUpdateTransactionError).map { println(it) }
}

