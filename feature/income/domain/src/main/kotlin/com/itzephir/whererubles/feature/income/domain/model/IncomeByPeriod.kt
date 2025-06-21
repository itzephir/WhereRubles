package com.itzephir.whererubles.feature.income.domain.model

import kotlinx.datetime.Instant

data class IncomeByPeriod(
    val total: String,
    val start: Instant,
    val end: Instant,
    val income: List<Income>,
)