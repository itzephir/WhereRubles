package com.itzephir.whererubles.feature.income.domain.model

import kotlin.time.Instant

/**
 * Income entity
 */
data class Income(
    val id: IncomeId,
    val title: String,
    val currency: String,
    val amount: String,
    val transactionDate: Instant,
    val comment: String?,
    val emoji: String,
    val account: Account,
    val category: Category,
)
