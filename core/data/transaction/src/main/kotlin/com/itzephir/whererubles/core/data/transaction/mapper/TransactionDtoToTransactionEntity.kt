package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.network.transaction.TransactionDto
import com.itzephir.whererubles.core.storage.transaction.TransactionEntity
import tech.mappie.api.ObjectMappie

object TransactionDtoToTransactionEntity: ObjectMappie<TransactionDto, TransactionEntity>()
