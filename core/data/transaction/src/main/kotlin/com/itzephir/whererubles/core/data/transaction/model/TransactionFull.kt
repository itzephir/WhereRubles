package com.itzephir.whererubles.core.data.transaction.model

import com.itzephir.whererubles.core.model.Currency
import com.itzephir.whererubles.core.model.Id
import kotlin.time.Instant

data class TransactionFull(
    val id: Id,
    val account: AccountBrief,
    val category: Category,
    val amount: Double,
    val transactionData: Instant,
    val comment: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    data class AccountBrief(
        val id: Id,
        val name: String,
        val balance: Double,
        val currency: Currency,
    )

    data class Category(
        val id: Id,
        val name: String,
        val emoji: String,
        val isIncome: Boolean,
    )
}