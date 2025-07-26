package com.itzephir.whererubles.feature.income.domain.model

import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Currency
import kotlin.time.Instant

/**
 * Income entity
 */
data class Income(
    val id: IncomeId,
    val title: String,
    val currency: Currency,
    val amount: Amount,
    val transactionDate: Instant,
    val comment: String?,
    val emoji: String,
    val account: Account,
    val category: Category,
)
