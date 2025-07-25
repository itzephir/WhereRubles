package com.itzephir.whererubles.feature.income.data.repository

import arrow.core.Either
import com.itzephir.whererubles.core.network.common.Id
import com.itzephir.whererubles.core.network.transaction.TransactionError
import com.itzephir.whererubles.core.network.transaction.TransactionResponseDto
import com.itzephir.whererubles.core.network.transaction.readTransactionsByAccountIdAndPeriod
import com.itzephir.whererubles.feature.income.data.mapper.map
import com.itzephir.whererubles.feature.income.domain.error.IncomeByAccountAndPeriodError
import com.itzephir.whererubles.feature.income.domain.model.AccountId
import com.itzephir.whererubles.feature.income.domain.model.Income
import com.itzephir.whererubles.feature.income.domain.repository.IncomeRepository
import io.ktor.client.HttpClient
import javax.inject.Inject
import kotlin.time.Instant

/**
 * Repository for setup income
 * @param httpClient client for http calls
 */
class RemoteIncomeRepository @Inject constructor(private val httpClient: HttpClient) : IncomeRepository {
    override suspend fun getByAccountIdAndPeriod(
        accountId: AccountId,
        start: Instant,
        end: Instant,
    ): Either<IncomeByAccountAndPeriodError, List<Income>> {
        return httpClient.readTransactionsByAccountIdAndPeriod(
            accountId = Id(accountId.value),
            start = start,
            end = end,
        )
            .mapLeft(TransactionError.ReadByAccountIdAndPeriodError::map)
            .map(List<TransactionResponseDto>::map)
    }
}
