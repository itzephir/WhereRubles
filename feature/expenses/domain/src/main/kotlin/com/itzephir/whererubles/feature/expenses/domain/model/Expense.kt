package com.itzephir.whererubles.feature.expenses.domain.model

import kotlinx.datetime.Instant

/**
 * Expense entity
 */
data class Expense(
    val id: ExpenseId,
    val title: String,
    val currency: String,
    val amount: String,
    val transactionDate: Instant,
    val comment: String?,
    val emoji: String,
)
