package com.itzephir.whererubles.domain.model

/**
 * List of expenses entity
 */
data class ExpensesList(
    val total: String,
    val expenses: List<TransactionResponse>,
)
