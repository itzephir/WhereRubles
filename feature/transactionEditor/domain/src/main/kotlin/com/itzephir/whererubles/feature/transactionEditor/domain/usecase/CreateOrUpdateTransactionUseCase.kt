package com.itzephir.whererubles.feature.transactionEditor.domain.usecase

import arrow.core.Either
import com.itzephir.whererubles.feature.transactionEditor.domain.error.CreateOrUpdateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.error.CreateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.error.UpdateTransactionError
import com.itzephir.whererubles.feature.transactionEditor.domain.mapper.map
import com.itzephir.whererubles.feature.transactionEditor.domain.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.domain.model.TransactionId
import com.itzephir.whererubles.feature.transactionEditor.domain.repository.TransactionRepository
import javax.inject.Inject

class CreateOrUpdateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {
    suspend operator fun invoke(
        id: TransactionId?,
        transaction: Transaction,
    ): Either<CreateOrUpdateTransactionError, Unit> = when (id) {
        null -> transactionRepository.createTransaction(transaction)
            .mapLeft(CreateTransactionError::map)

        else -> transactionRepository.updateTransaction(id, transaction)
            .mapLeft(UpdateTransactionError::map)
    }
}