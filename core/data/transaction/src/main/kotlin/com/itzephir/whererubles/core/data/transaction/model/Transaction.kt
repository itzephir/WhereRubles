package com.itzephir.whererubles.core.data.transaction.model

import com.itzephir.whererubles.core.model.Currency
import com.itzephir.whererubles.core.model.Id
import kotlin.time.Instant

data class Transaction(
    val id: Id,
    val accountId: Id,
    val categoryId: Id,
    val amount: Double,
    val title: String,
    val currency: Currency,
    val emoji: String,
    val account: Account,
    val category: Category,
    val transactionDate: Instant,
    val comment: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
){
    data class Account(
        val id: Id,
        val currency: Currency,
        val name: String,
    )

    data class Category(
        val id: Id,
        val name: String,
    )
}