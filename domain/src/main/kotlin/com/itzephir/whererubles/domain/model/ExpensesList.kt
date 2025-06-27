package com.itzephir.whererubles.domain.model

data class ExpensesList(
    val total: String,
    val expenses: List<TransactionResponse>,
)
