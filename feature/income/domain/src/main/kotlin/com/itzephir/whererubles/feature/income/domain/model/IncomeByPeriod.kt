package com.itzephir.whererubles.feature.income.domain.model

import kotlinx.datetime.Instant

/**
 * List of income by period entity
 */
data class IncomeByPeriod(
    val total: String,
    val currency: String,
    val start: Instant,
    val end: Instant,
    val income: List<Income>,
)
