package com.itzephir.whererubles.feature.income.domain.model

import com.itzephir.whererubles.core.model.Currency
import kotlin.time.Instant

/**
 * List of income by period entity
 */
data class IncomeByPeriod(
    val total: String,
    val currency: Currency,
    val start: Instant,
    val end: Instant,
    val income: List<Income>,
    val account: Account,
)
