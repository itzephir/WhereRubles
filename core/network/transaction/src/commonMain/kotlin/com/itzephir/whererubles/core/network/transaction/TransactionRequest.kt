package com.itzephir.whererubles.core.network.transaction

import com.itzephir.whererubles.core.network.common.Id
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TransactionRequest(
    val accountId: Id,
    val categoryId: Id,
    val amount: String,
    val transactionDate: Instant,
    val comment: String?,
)