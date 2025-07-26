package com.itzephir.whererubles.core.storage.storage.transaction

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.transaction.TransactionEntity
import com.itzephir.whererubles.core.storage.transaction.TransactionRequest
import com.itzephir.whererubles.core.storage.transaction.TransactionWithAccountAndCategory
import kotlin.time.Instant

interface TransactionStorage {
    suspend fun readByAccountIdAndPeriod(
        accountId: Id,
        start: Instant,
        end: Instant,
    ): List<TransactionWithAccountAndCategory>

    suspend fun create(transactionRequest: TransactionRequest): TransactionEntity
    suspend fun readAll(): List<TransactionEntity>
    suspend fun readById(id: Id): TransactionWithAccountAndCategory?
    suspend fun updateById(id: Id, transactionRequest: TransactionRequest): TransactionWithAccountAndCategory?
    suspend fun update(transactionEntity: TransactionEntity)
    suspend fun deleteById(id: Id)
    suspend fun replaceAllByIdAndPeriod(
        accountId: Id,
        start: Instant,
        end: Instant,
        transactions: List<TransactionEntity>,
    )
}