package com.itzephir.whererubles.feature.expenses.domain.model

/**
 * List of today expenses entity
 */
data class ExpensesToday(
    val total: String,
    val currency: String,
    val expenses: List<Expense>,
    val account: Account,
)
