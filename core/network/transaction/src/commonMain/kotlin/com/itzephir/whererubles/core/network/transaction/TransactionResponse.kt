package com.itzephir.whererubles.core.network.transaction

import com.itzephir.whererubles.core.network.common.Id
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponse(
    val id: Id,
    val account: AccountBrief,
    val category: Category,
    val amount: String,
    val transactionDate: Instant,
    val comment: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    @Serializable
    data class AccountBrief(
        val id: Id,
        val name: String,
        val balance: String,
        val currency: String,
    )

    @Serializable
    data class Category(
        val id: Id,
        val name: String,
        val emoji: String,
        val isIncome: Boolean,
    )
}