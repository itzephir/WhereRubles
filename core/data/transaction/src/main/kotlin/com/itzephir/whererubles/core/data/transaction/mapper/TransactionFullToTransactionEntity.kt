package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.model.TransactionFull
import com.itzephir.whererubles.core.storage.transaction.TransactionEntity
import tech.mappie.api.ObjectMappie

object TransactionFullToTransactionEntity : ObjectMappie<TransactionFull, TransactionEntity>() {
    override fun map(from: TransactionFull): TransactionEntity = mapping {
        to::accountId fromProperty from.account::id
        to::categoryId fromProperty from.category::id
    }
}