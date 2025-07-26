package com.itzephir.whererubles.core.network.transaction

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.model.Amount
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class TransactionRequest(
    val accountId: Id,
    val categoryId: Id,
    val amount: Amount,
    @Contextual val transactionDate: Instant,
    val comment: String?,
)