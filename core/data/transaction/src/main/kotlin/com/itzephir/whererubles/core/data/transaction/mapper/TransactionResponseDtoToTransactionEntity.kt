package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.network.transaction.TransactionResponseDto
import com.itzephir.whererubles.core.storage.transaction.TransactionEntity
import tech.mappie.api.ObjectMappie

object TransactionResponseDtoToTransactionEntity: ObjectMappie<TransactionResponseDto, TransactionEntity>() {
    override fun map(from: TransactionResponseDto): TransactionEntity = mapping {
        to::accountId fromProperty from.account::id
        to::categoryId fromProperty from.category::id
    }
}