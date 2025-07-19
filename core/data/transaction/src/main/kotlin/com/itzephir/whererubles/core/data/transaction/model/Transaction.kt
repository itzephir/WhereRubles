package com.itzephir.whererubles.core.data.transaction.model

import com.itzephir.whererubles.core.model.Id
import kotlin.time.Instant

data class Transaction(
    val id: Id,
    val accountId: Id,
    val categoryId: Id,
    val amount: Double,
    val transactionDate: Instant,
    val comment: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
)