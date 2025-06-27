package com.itzephir.whererubles.domain.model

data class IncomeList(
    val total: String,
    val income: List<TransactionResponse>,
)
