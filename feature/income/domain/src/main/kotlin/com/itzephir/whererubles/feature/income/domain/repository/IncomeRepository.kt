package com.itzephir.whererubles.feature.income.domain.repository

import arrow.core.Either
import com.itzephir.whererubles.feature.income.domain.error.IncomeByAccountAndPeriodError
import com.itzephir.whererubles.feature.income.domain.model.AccountId
import com.itzephir.whererubles.feature.income.domain.model.Income
import kotlin.time.Instant

/**
 * Income repository contract
 */
interface IncomeRepository {
    suspend fun getByAccountIdAndPeriod(
        accountId: AccountId,
        start: Instant,
        end: Instant,
    ): Either<IncomeByAccountAndPeriodError, List<Income>>
}
