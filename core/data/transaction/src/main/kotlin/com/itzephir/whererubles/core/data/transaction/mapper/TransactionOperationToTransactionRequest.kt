package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.model.TransactionOperation
import com.itzephir.whererubles.core.storage.transaction.TransactionRequest
import tech.mappie.api.ObjectMappie

object TransactionOperationToTransactionRequest: ObjectMappie<TransactionOperation, TransactionRequest>()
