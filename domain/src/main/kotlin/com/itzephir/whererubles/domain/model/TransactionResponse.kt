package com.itzephir.whererubles.domain.model

import kotlinx.datetime.Instant

data class TransactionResponse(
    val id: TransactionId,
    val account: AccountBrief,
    val category: Category,
    val amount: String,
    val transactionDate: Instant,
    val comment: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
)
