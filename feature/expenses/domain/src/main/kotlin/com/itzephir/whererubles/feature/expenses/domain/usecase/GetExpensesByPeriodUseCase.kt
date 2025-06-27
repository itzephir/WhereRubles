package com.itzephir.whererubles.feature.expenses.domain.usecase

import arrow.core.Either
import arrow.core.raise.either
import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesByAccountAndPeriodError
import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesByPeriodError
import com.itzephir.whererubles.feature.expenses.domain.mapper.toExpensesByPeriodError
import com.itzephir.whererubles.feature.expenses.domain.model.ExpensesByPeriod
import com.itzephir.whererubles.feature.expenses.domain.repository.AccountRepository
import com.itzephir.whererubles.feature.expenses.domain.repository.ExpensesRepository
import kotlinx.datetime.Instant
import java.util.Locale

class GetExpensesByPeriodUseCase(
    private val accountRepository: AccountRepository,
    private val expensesRepository: ExpensesRepository,
) {
    suspend operator fun invoke(
        start: Instant,
        end: Instant,
    ): Either<ExpensesByPeriodError, ExpensesByPeriod> = either {
        val account = accountRepository.current() ?: raise(ExpensesByPeriodError.NoAccount)

        val expenses = expensesRepository.getByAccountIdAndPeriod(
            accountId = account,
            start = start,
            end = end,
        ).mapLeft(ExpensesByAccountAndPeriodError::toExpensesByPeriodError).bind()

        val totalAmount = expenses.fold(initial = 0.0) { acc, expense ->
            acc + expense.amount.toDouble()
        }.let { String.format(Locale.US, "%.2f", it) }

        ExpensesByPeriod(totalAmount, start, end, expenses)
    }
}
