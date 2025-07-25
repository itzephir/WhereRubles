package com.itzephir.whererubles.core.network.repository.transaction

import arrow.core.Either
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.network.transaction.TransactionDto
import com.itzephir.whererubles.core.network.transaction.TransactionError
import com.itzephir.whererubles.core.network.transaction.TransactionRequest
import com.itzephir.whererubles.core.network.transaction.TransactionResponseDto
import com.itzephir.whererubles.core.network.transaction.createTransaction
import com.itzephir.whererubles.core.network.transaction.deleteTransactionById
import com.itzephir.whererubles.core.network.transaction.readTransactionById
import com.itzephir.whererubles.core.network.transaction.readTransactionsByAccountIdAndPeriod
import com.itzephir.whererubles.core.network.transaction.updateTransactionById
import io.ktor.client.HttpClient
import kotlin.time.Instant

class RemoteTransactionRepository(
    private val httpClient: HttpClient,
) : TransactionRepository {
    override suspend fun create(transactionRequest: TransactionRequest)
            : Either<TransactionError.CreateError, TransactionDto> =
        httpClient.createTransaction(transactionRequest)

    override suspend fun readById(id: Id)
            : Either<TransactionError.ReadByIdError, TransactionResponseDto> =
        httpClient.readTransactionById(id)

    override suspend fun updateById(
        id: Id,
        transactionRequest: TransactionRequest,
    ): Either<TransactionError.UpdateByIdError, TransactionResponseDto> =
        httpClient.updateTransactionById(id, transactionRequest)

    override suspend fun deleteById(id: Id): Either<TransactionError.DeleteByIdError, Unit> =
        httpClient.deleteTransactionById(id)

    override suspend fun readByAccountIdAndPeriod(
        accountId: Id,
        start: Instant?,
        end: Instant?,
    ): Either<TransactionError.ReadByAccountIdAndPeriodError, List<TransactionResponseDto>> =
        httpClient.readTransactionsByAccountIdAndPeriod(accountId, start, end)
}