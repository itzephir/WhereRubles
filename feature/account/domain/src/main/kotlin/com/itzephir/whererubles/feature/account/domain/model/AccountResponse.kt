package com.itzephir.whererubles.feature.account.domain.model

import kotlin.time.Instant

data class AccountResponse(
    val id: AccountId,
    val name: String,
    val balance: String,
    val currency: Currency,
    val incomeStats: List<StatItem>,
    val expenseStats: List<StatItem>,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    data class StatItem(
        val categoryId: CategoryId,
        val categoryName: String,
        val emoji: String,
        val amount: String,
    ) {
        @JvmInline
        value class CategoryId(val value: Int)
    }
}
