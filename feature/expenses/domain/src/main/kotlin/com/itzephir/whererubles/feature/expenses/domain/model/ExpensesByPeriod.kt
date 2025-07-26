package com.itzephir.whererubles.feature.expenses.domain.model

import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Currency
import kotlin.time.Instant

/**
 * List of expenses by defined period entity
 */
data class ExpensesByPeriod(
    val total: Amount,
    val currency: Currency,
    val start: Instant,
    val end: Instant,
    val expenses: List<Expense>,
    val account: Account,
)
