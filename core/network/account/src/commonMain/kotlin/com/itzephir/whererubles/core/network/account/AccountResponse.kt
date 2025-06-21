package com.itzephir.whererubles.core.network.account

import com.itzephir.whererubles.core.network.common.Id
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class AccountResponse(
    val id: Id,
    val name: String,
    val balance: String,
    val currency: String,
    val incomeStats: List<StatItem>,
    val expenseStats: List<StatItem>,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    @Serializable
    data class StatItem(
        val categoryId: Id,
        val categoryName: String,
        val emoji: String,
        val amount: String,
    )
}