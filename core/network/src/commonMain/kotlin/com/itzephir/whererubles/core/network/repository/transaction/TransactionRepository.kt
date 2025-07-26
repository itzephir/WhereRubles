package com.itzephir.whererubles.core.network.repository.transaction

import arrow.core.Either
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.network.transaction.TransactionDto
import com.itzephir.whererubles.core.network.transaction.TransactionError
import com.itzephir.whererubles.core.network.transaction.TransactionRequest
import com.itzephir.whererubles.core.network.transaction.TransactionResponseDto
import kotlin.time.Instant

interface TransactionRepository {
    suspend fun create(transactionRequest: TransactionRequest): Either<TransactionError.CreateError, TransactionDto>
    suspend fun readById(id: Id): Either<TransactionError.ReadByIdError, TransactionResponseDto>
    suspend fun updateById(
        id: Id,
        transactionRequest: TransactionRequest,
    ): Either<TransactionError.UpdateByIdError, TransactionResponseDto>

    suspend fun deleteById(id: Id): Either<TransactionError.DeleteByIdError, Unit>
    suspend fun readByAccountIdAndPeriod(
        accountId: Id,
        start: Instant? = null,
        end: Instant? = null,
    ): Either<TransactionError.ReadByAccountIdAndPeriodError, List<TransactionResponseDto>>
}