package com.itzephir.whererubles.feature.expenses.domain.usecase

import arrow.core.Either
import arrow.core.raise.either
import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesByAccountAndPeriodError
import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesByPeriodError
import com.itzephir.whererubles.feature.expenses.domain.mapper.toExpensesByPeriodError
import com.itzephir.whererubles.feature.expenses.domain.model.ExpensesByPeriod
import com.itzephir.whererubles.feature.expenses.domain.repository.AccountRepository
import com.itzephir.whererubles.feature.expenses.domain.repository.ExpensesRepository
import kotlin.time.Instant
import java.util.Locale
import javax.inject.Inject

/**
 * Use case for getting expenses by defined period
 * @param accountRepository repository to interact with account
 * @param expensesRepository repository to interact with expenses
 */
class GetExpensesByPeriodUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val expensesRepository: ExpensesRepository,
) {
    suspend operator fun invoke(
        start: Instant,
        end: Instant,
    ): Either<ExpensesByPeriodError, ExpensesByPeriod> = either {
        val account = accountRepository.current() ?: raise(ExpensesByPeriodError.NoAccount)

        val expenses = expensesRepository.getByAccountIdAndPeriod(
            accountId = account.id,
            start = start,
            end = end,
        ).mapLeft(ExpensesByAccountAndPeriodError::toExpensesByPeriodError).bind()

        val totalAmount = expenses.fold(initial = 0.0) { acc, expense ->
            acc + expense.amount.toDouble()
        }.let { String.format(Locale.US, "%.2f", it) }

        ExpensesByPeriod(totalAmount, currency = account.currency,start, end, expenses)
    }
}
