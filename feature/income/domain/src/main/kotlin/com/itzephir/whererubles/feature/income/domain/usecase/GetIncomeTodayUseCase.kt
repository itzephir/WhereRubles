package com.itzephir.whererubles.feature.income.domain.usecase

import arrow.core.Either
import arrow.core.raise.either
import com.itzephir.whererubles.feature.income.domain.error.IncomeByAccountAndPeriodError
import com.itzephir.whererubles.feature.income.domain.error.IncomeTodayError
import com.itzephir.whererubles.feature.income.domain.mapper.toExpensesTodayError
import com.itzephir.whererubles.feature.income.domain.model.IncomeToday
import com.itzephir.whererubles.feature.income.domain.repository.AccountRepository
import com.itzephir.whererubles.feature.income.domain.repository.IncomeRepository
import kotlin.time.Clock
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import java.util.Locale
import javax.inject.Inject
import kotlin.time.Duration.Companion.days

/**
 * Use case for getting today income
 * @param accountRepository repository to interact with account
 * @param incomeRepository repository to interact with income
 */
class GetIncomeTodayUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val incomeRepository: IncomeRepository,
) {
    suspend operator fun invoke(): Either<IncomeTodayError, IncomeToday> = either {
        val account =
            accountRepository.current() ?: raise(IncomeTodayError.NoAccount)

        val income = incomeRepository.getByAccountIdAndPeriod(
            account.id,
            start = startOfTheDay(),
            end = endOfTheDay(),
        ).mapLeft(IncomeByAccountAndPeriodError::toExpensesTodayError).bind()

        val totalAmount = income.fold(initial = 0.0) { acc, income ->
            acc + income.amount.toDouble()
        }.let { String.format(Locale.US, "%.2f", it) }

        IncomeToday(totalAmount, account.currency, income, account)
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

