package com.itzephir.whererubles.feature.income.domain.model

import kotlinx.datetime.Instant

data class Income(
    val id: IncomeId,
    val title: String,
    val currency: String,
    val amount: String,
    val transactionDate: Instant,
    val comment: String?,
    val emoji: String,
)
