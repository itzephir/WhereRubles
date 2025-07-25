package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.network.transaction.TransactionRequest
import com.itzephir.whererubles.core.storage.transaction.TransactionOperationEntity
import tech.mappie.api.ObjectMappie

object TransactionOperationBodyToTransactionRequest :
    ObjectMappie<TransactionOperationEntity.TransactionOperationBody, TransactionRequest>()