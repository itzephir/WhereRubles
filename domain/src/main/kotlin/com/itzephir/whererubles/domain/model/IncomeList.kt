package com.itzephir.whererubles.domain.model

/**
 * List of income entity
 */
data class IncomeList(
    val total: String,
    val income: List<TransactionResponse>,
)
