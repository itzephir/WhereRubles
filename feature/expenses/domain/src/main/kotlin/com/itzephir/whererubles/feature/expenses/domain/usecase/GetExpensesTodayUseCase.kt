package com.itzephir.whererubles.feature.expenses.domain.usecase

import arrow.core.Either
import arrow.core.raise.either
import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesByAccountAndPeriodError
import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesTodayError
import com.itzephir.whererubles.feature.expenses.domain.mapper.toExpensesTodayError
import com.itzephir.whererubles.feature.expenses.domain.model.ExpensesToday
import com.itzephir.whererubles.feature.expenses.domain.repository.AccountRepository
import com.itzephir.whererubles.feature.expenses.domain.repository.ExpensesRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import java.util.Locale
import kotlin.time.Duration.Companion.days

/**
 * Use case for getting today expenses
 * @param accountRepository repository for interacting with account
 * @param expensesRepository repository for interacting with expenses
 */
class GetExpensesTodayUseCase(
    private val accountRepository: AccountRepository,
    private val expensesRepository: ExpensesRepository,
) {
    suspend operator fun invoke(): Either<ExpensesTodayError, ExpensesToday> = either {
        val account =
            accountRepository.current() ?: raise(ExpensesTodayError.NoAccount)

        val expenses = expensesRepository.getByAccountIdAndPeriod(
            account.id,
            start = startOfTheDay(),
            end = endOfTheDay(),
        ).mapLeft(ExpensesByAccountAndPeriodError::toExpensesTodayError).bind()

        val totalAmount = expenses.fold(initial = 0.0) { acc, expense ->
            acc + expense.amount.toDouble()
        }.let { String.format(Locale.US, "%.2f", it) }

        ExpensesToday(totalAmount, currency = account.currency,expenses)
    }

    private fun startOfTheDay(): Instant {
        val now = Clock.System.now()
        val currentDate = now
            .toLocalDateTime(TimeZone.currentSystemDefault()).date
        return currentDate
            .atStartOfDayIn(TimeZone.currentSystemDefault())
    }

    private fun endOfTheDay(): Instant {
        val now = Clock.System.now()
        val tomorrow = now + 1.days
        val date = tomorrow
            .toLocalDateTime(TimeZone.currentSystemDefault()).date
        return date
            .atStartOfDayIn(TimeZone.currentSystemDefault())
    }
}

