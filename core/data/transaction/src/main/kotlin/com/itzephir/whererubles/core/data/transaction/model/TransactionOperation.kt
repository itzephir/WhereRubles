package com.itzephir.whererubles.core.data.transaction.model

import com.itzephir.whererubles.core.model.Id
import kotlin.time.Instant

data class TransactionOperation(
    val accountId: Id,
    val categoryId: Id,
    val amount: Double,
    val transactionDate: Instant,
    val comment: String?,
)