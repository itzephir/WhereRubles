package com.itzephir.whererubles.feature.expenses.domain.model

data class ExpensesToday(
    val total: String,
    val expenses: List<Expense>
)
