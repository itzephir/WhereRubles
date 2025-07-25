package com.itzephir.whererubles.feature.income.domain.usecase

import arrow.core.Either
import arrow.core.raise.either
import com.itzephir.whererubles.feature.income.domain.error.IncomeByAccountAndPeriodError
import com.itzephir.whererubles.feature.income.domain.error.IncomeByPeriodError
import com.itzephir.whererubles.feature.income.domain.mapper.toExpensesByPeriodError
import com.itzephir.whererubles.feature.income.domain.model.IncomeByPeriod
import com.itzephir.whererubles.feature.income.domain.repository.AccountRepository
import com.itzephir.whererubles.feature.income.domain.repository.IncomeRepository
import kotlin.time.Instant
import java.util.Locale
import javax.inject.Inject

/**
 * Use case for getting income by given period
 * @param accountRepository repository to interact with account
 * @param incomeRepository repository to interact with income
 */
class GetIncomeByPeriodUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val incomeRepository: IncomeRepository,
) {
    suspend operator fun invoke(
        start: Instant,
        end: Instant,
    ): Either<IncomeByPeriodError, IncomeByPeriod> = either {
        val account = accountRepository.current() ?: raise(IncomeByPeriodError.NoAccount)

        val income = incomeRepository.getByAccountIdAndPeriod(
            accountId = account.id,
            start = start,
            end = end,
        ).mapLeft(IncomeByAccountAndPeriodError::toExpensesByPeriodError).bind()

        val totalAmount = income.fold(initial = 0.0) { acc, income ->
            acc + income.amount
        }.let { String.format(Locale.US, "%.2f", it) }

        IncomeByPeriod(totalAmount, account.currency, start, end, income, account)
    }
}
