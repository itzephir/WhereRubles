package com.itzephir.whererubles.feature.expenses.domain.model

import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Currency
import kotlin.time.Instant

/**
 * Expense entity
 */
data class Expense(
    val id: ExpenseId,
    val title: String,
    val currency: Currency,
    val amount: Amount,
    val transactionDate: Instant,
    val comment: String?,
    val emoji: String,
    val account: Account,
    val category: Category,
)
