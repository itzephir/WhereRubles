package com.itzephir.whererubles.core.storage.storage.transaction

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.transaction.TransactionOperationDao
import com.itzephir.whererubles.core.storage.transaction.TransactionOperationEntity

class LocalTransactionOperationStorage(
    private val transactionOperationDao: TransactionOperationDao,
) : TransactionOperationStorage {
    override suspend fun readAll(): List<TransactionOperationEntity> =
        transactionOperationDao.getAll()

    override suspend fun create(transactionOperationEntity: TransactionOperationEntity): Unit =
        transactionOperationDao.upsert(operation = transactionOperationEntity)

    override suspend fun deleteById(id: Id): Unit =
        transactionOperationDao.deleteById(id)
}