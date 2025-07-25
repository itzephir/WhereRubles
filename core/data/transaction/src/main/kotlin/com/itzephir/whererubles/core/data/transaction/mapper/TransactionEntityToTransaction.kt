package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.model.Transaction
import com.itzephir.whererubles.core.storage.transaction.TransactionEntity
import tech.mappie.api.ObjectMappie

object TransactionEntityToTransaction: ObjectMappie<TransactionEntity, Transaction>()