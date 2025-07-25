package com.itzephir.whererubles.core.network.account

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Currency
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class AccountResponse(
    val id: Id,
    val name: String,
    val balance: Amount,
    val currency: Currency,
    val incomeStats: List<StatItem>,
    val expenseStats: List<StatItem>,
    @Contextual val createdAt: Instant,
    @Contextual val updatedAt: Instant,
) {
    @Serializable
    data class StatItem(
        val categoryId: Id,
        val categoryName: String,
        val emoji: String,
        val amount: Amount,
    )
}