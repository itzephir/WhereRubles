package com.itzephir.whererubles.domain.model

import kotlinx.datetime.Instant

@Suppress("unused")
data class TransactionRequest(
    val accountId: AccountId,
    val categoryId: CategoryId,
    val amount: String,
    val transactionDate: Instant,
    val comment: String? = null
)
