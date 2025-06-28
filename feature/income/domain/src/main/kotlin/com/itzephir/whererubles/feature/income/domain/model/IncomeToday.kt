package com.itzephir.whererubles.feature.income.domain.model

/**
 * List of today income entity
 */
data class IncomeToday(
    val total: String,
    val income: List<Income>
)
