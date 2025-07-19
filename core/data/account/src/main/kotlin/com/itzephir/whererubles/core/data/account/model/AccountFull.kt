package com.itzephir.whererubles.core.data.account.model

import com.itzephir.whererubles.core.model.Currency
import com.itzephir.whererubles.core.model.Id
import kotlin.time.Instant

data class AccountFull(
    val id: Id,
    val name: String,
    val balance: Double,
    val currency: Currency,
    val incomeStats: List<StatItem>,
    val expenseStats: List<StatItem>,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    data class StatItem(
        val categoryId: Id,
        val categoryName: String,
        val emoji: String,
        val amount: Double,
    )
}