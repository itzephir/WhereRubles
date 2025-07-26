package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.model.TransactionOperation
import com.itzephir.whererubles.core.storage.transaction.TransactionOperationEntity
import tech.mappie.api.ObjectMappie

object TransactionOperationToTransactionOperationBody :
    ObjectMappie<TransactionOperation, TransactionOperationEntity.TransactionOperationBody>()
