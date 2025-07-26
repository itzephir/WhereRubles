package com.itzephir.whererubles.feature.account.domain.model

import com.itzephir.whererubles.core.model.Amount
import kotlin.time.Instant

data class AccountResponse(
    val id: AccountId,
    val name: String,
    val balance: Amount,
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
        val amount: Amount,
    ) {
        @JvmInline
        value class CategoryId(val value: Int)
    }
}
