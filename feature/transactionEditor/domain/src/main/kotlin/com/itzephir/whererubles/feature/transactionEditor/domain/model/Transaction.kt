package com.itzephir.whererubles.feature.transactionEditor.domain.model

import kotlin.time.Instant


data class Transaction(
    val accountId: AccountId,
    val categoryId: CategoryId,
    val amount: String,
    val transactionDate: Instant,
    val comment: String?,
) {
    @JvmInline
    value class AccountId(val value: Int)

    @JvmInline
    value class CategoryId(val value: Int)
}