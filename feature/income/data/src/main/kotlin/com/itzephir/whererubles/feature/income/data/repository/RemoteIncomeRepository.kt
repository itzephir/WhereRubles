package com.itzephir.whererubles.feature.income.data.repository

import arrow.core.Either
import arrow.core.right
import com.itzephir.whererubles.core.data.transaction.TransactionInteractor
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.feature.income.data.mapper.TransactionToIncome
import com.itzephir.whererubles.feature.income.domain.error.IncomeByAccountAndPeriodError
import com.itzephir.whererubles.feature.income.domain.model.AccountId
import com.itzephir.whererubles.feature.income.domain.model.Income
import com.itzephir.whererubles.feature.income.domain.repository.IncomeRepository
import kotlinx.coroutines.flow.last
import javax.inject.Inject
import kotlin.time.Instant

class RemoteIncomeRepository @Inject constructor(
    private val transactionInteractor: TransactionInteractor,
) : IncomeRepository {
    override suspend fun getByAccountIdAndPeriod(
        accountId: AccountId,
        start: Instant,
        end: Instant,
    ): Either<IncomeByAccountAndPeriodError, List<Income>> {
        return transactionInteractor.getByAccountIdAndPeriod(
            accountId = Id(accountId.value),
            start = start,
            end = end,
        )
            .last()
            .let(TransactionToIncome::mapList)
            .right()
    }
}
