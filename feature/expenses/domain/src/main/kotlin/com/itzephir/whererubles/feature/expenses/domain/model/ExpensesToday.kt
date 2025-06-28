package com.itzephir.whererubles.feature.expenses.domain.model

/**
 * List of today expenses entity
 */
data class ExpensesToday(
    val total: String,
    val expenses: List<Expense>
)
