package com.itzephir.whererubles.core.network.transaction

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Currency
import kotlinx.serialization.Contextual
import kotlin.time.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponseDto(
    val id: Id,
    val account: AccountBrief,
    val category: Category,
    val amount: Amount,
    @Contextual val transactionDate: Instant,
    val comment: String?,
    @Contextual val createdAt: Instant,
    @Contextual val updatedAt: Instant,
) {
    @Serializable
    data class AccountBrief(
        val id: Id,
        val name: String,
        val balance: Amount,
        val currency: Currency,
    )

    @Serializable
    data class Category(
        val id: Id,
        val name: String,
        val emoji: String,
        val isIncome: Boolean,
    )
}