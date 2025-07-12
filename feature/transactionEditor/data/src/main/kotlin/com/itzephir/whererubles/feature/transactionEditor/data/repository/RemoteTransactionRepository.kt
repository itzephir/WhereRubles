package com.itzephir.whererubles.feature.transactionEditor.data.repository

import arrow.core.Either
import com.itzephir.whererubles.core.network.common.Id
import com.itzephir.whererubles.core.network.transaction.TransactionError
import com.itzephir.whererubles.core.network.transaction.createTransaction
import com.itzephir.whererubles.core.network.transaction.updateTransactionById
import com.itzephir.whererubles.feature.transactionEditor.data.mapper.map
import com.itzephir.whererubles.feature.transactionEditor.domain.error.CreateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.error.UpdateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.domain.model.TransactionId
import com.itzephir.whererubles.feature.transactionEditor.domain.repository.TransactionRepository
import io.ktor.client.HttpClient
import javax.inject.Inject

class RemoteTransactionRepository @Inject constructor(
    private val httpClient: HttpClient,
) : TransactionRepository {
    override suspend fun createTransaction(
        transaction: Transaction,
    ): Either<CreateTransactionError, Unit> =
        httpClient.createTransaction(
            transaction = transaction.map()
        ).mapLeft(TransactionError.CreateError::map).map {}

    override suspend fun updateTransaction(
        transactionId: TransactionId,
        transaction: Transaction,
    ): Either<UpdateTransactionError, Unit> =
        httpClient.also { println(it) }.updateTransactionById(
            id = Id(transactionId.value),
            transaction = transaction.map()
        ).mapLeft(TransactionError.UpdateByIdError::map).map {println(it)}
}
