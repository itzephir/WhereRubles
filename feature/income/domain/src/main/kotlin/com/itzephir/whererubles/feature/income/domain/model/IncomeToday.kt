package com.itzephir.whererubles.feature.income.domain.model

import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Currency

/**
 * List of today income entity
 */
data class IncomeToday(
    val total: Amount,
    val currency: Currency,
    val income: List<Income>,
    val account: Account,
)
