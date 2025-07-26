package com.itzephir.whererubles.feature.expenses.domain.model

import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Currency

/**
 * List of today expenses entity
 */
data class ExpensesToday(
    val total: Amount,
    val currency: Currency,
    val expenses: List<Expense>,
    val account: Account,
)
