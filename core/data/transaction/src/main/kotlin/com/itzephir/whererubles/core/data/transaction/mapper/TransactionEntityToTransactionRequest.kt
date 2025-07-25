package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.network.transaction.TransactionRequest
import com.itzephir.whererubles.core.storage.transaction.TransactionEntity
import tech.mappie.api.ObjectMappie

object TransactionEntityToTransactionRequest :
    ObjectMappie<TransactionEntity, TransactionRequest>()
