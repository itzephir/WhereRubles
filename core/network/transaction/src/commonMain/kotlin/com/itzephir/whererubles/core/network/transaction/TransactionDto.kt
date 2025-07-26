package com.itzephir.whererubles.core.network.transaction

import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Id
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class TransactionDto(
    val id: Id,
    val accountId: Id,
    val categoryId: Id,
    val amount: Amount,
    @Contextual val transactionDate: Instant,
    val comment: String?,
    @Contextual val createdAt: Instant,
    @Contextual val updatedAt: Instant,
)