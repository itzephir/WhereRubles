package com.itzephir.whererubles.core.storage.storage.transaction

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.mapper.TransactionRequestToTransactionEntity
import com.itzephir.whererubles.core.storage.transaction.TransactionDao
import com.itzephir.whererubles.core.storage.transaction.TransactionEntity
import com.itzephir.whererubles.core.storage.transaction.TransactionRequest
import com.itzephir.whererubles.core.storage.transaction.TransactionWithAccountAndCategory
import kotlin.time.Instant

class LocalTransactionStorage(
    private val transactionDao: TransactionDao,
) : TransactionStorage {
    override suspend fun readByAccountIdAndPeriod(
        accountId: Id,
        start: Instant,
        end: Instant,
    ): List<TransactionWithAccountAndCategory> =
        transactionDao.getTransactionsByAccountIdAndPeriod(accountId, start, end)

    override suspend fun create(transactionRequest: TransactionRequest): TransactionEntity =
        TransactionRequestToTransactionEntity.map(from = transactionRequest).also {
            transactionDao.upsert(it)
        }

    override suspend fun readAll(): List<TransactionEntity> =
        transactionDao.getAllTransactions()

    override suspend fun readById(id: Id): TransactionWithAccountAndCategory? =
        transactionDao.getTransactionWithAccountAndCategoryById(id)

    override suspend fun updateById(
        id: Id,
        transactionRequest: TransactionRequest,
    ): TransactionWithAccountAndCategory? = transactionDao.findOneAndUpsert(id = id, transactionRequest)

    override suspend fun update(transactionEntity: TransactionEntity) =
        transactionDao.upsert(transactionEntity)

    override suspend fun deleteById(id: Id): Unit = transactionDao.deleteById(id)

    override suspend fun replaceAllByIdAndPeriod(
        accountId: Id,
        start: Instant,
        end: Instant,
        transactions: List<TransactionEntity>,
    ): Unit = transactionDao.deleteAllByAccountIdAndPeriodAndUpsertAll(
        accountId = accountId,
        start = start,
        end = end,
        transactions = transactions,
    )
}