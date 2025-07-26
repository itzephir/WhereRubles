package com.itzephir.whererubles.core.storage.storage.transaction

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.transaction.TransactionOperationEntity

interface TransactionOperationStorage {
    suspend fun readAll(): List<TransactionOperationEntity>
    suspend fun create(transactionOperationEntity: TransactionOperationEntity)
    suspend fun deleteById(id: Id)
}