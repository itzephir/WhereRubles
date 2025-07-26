package com.itzephir.whererubles.core.storage.mapper

import com.itzephir.whererubles.core.storage.transaction.TransactionEntity
import com.itzephir.whererubles.core.storage.transaction.TransactionRequest
import tech.mappie.api.ObjectMappie
import kotlin.time.Clock

object TransactionRequestToTransactionEntity :
    ObjectMappie<TransactionRequest, TransactionEntity>() {
    override fun map(from: TransactionRequest): TransactionEntity = mapping {
        to::createdAt fromValue Clock.System.now()
        to::updatedAt fromValue Clock.System.now()
    }
}