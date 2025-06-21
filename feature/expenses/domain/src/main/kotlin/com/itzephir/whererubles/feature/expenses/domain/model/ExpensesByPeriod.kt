package com.itzephir.whererubles.feature.expenses.domain.model

import kotlinx.datetime.Instant

data class ExpensesByPeriod(
    val total: String,
    val start: Instant,
    val end: Instant,
    val expenses: List<Expense>,
)