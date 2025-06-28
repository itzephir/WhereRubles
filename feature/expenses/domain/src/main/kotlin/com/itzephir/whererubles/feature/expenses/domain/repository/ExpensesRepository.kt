package com.itzephir.whererubles.feature.expenses.domain.repository

import arrow.core.Either
import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesByAccountAndPeriodError
import com.itzephir.whererubles.feature.expenses.domain.model.AccountId
import com.itzephir.whererubles.feature.expenses.domain.model.Expense
import kotlinx.datetime.Instant

/**
 * Expenses Repository contract
 */
interface ExpensesRepository {
    suspend fun getByAccountIdAndPeriod(
        accountId: AccountId,
        start: Instant,
        end: Instant,
    ): Either<ExpensesByAccountAndPeriodError, List<Expense>>
}
