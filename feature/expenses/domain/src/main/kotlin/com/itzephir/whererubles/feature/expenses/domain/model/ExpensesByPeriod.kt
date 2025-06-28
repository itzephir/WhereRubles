package com.itzephir.whererubles.feature.expenses.domain.model

import kotlinx.datetime.Instant

/**
 * List of expenses by defined period entity
 */
data class ExpensesByPeriod(
    val total: String,
    val start: Instant,
    val end: Instant,
    val expenses: List<Expense>,
)
