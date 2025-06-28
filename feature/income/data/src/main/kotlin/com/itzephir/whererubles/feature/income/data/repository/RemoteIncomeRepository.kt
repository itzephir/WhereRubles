package com.itzephir.whererubles.feature.income.data.repository

import arrow.core.Either
import com.itzephir.whererubles.core.network.common.Id
import com.itzephir.whererubles.core.network.transaction.TransactionError
import com.itzephir.whererubles.core.network.transaction.TransactionResponse
import com.itzephir.whererubles.core.network.transaction.readTransactionsByAccountIdAndPeriod
import com.itzephir.whererubles.feature.income.data.mapper.map
import com.itzephir.whererubles.feature.income.domain.error.IncomeByAccountAndPeriodError
import com.itzephir.whererubles.feature.income.domain.model.AccountId
import com.itzephir.whererubles.feature.income.domain.model.Income
import com.itzephir.whererubles.feature.income.domain.repository.IncomeRepository
import io.ktor.client.HttpClient
import kotlinx.datetime.Instant

/**
 * Repository for setup income
 * @param httpClient client for http calls
 */
class RemoteIncomeRepository(private val httpClient: HttpClient) : IncomeRepository {
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
            .map(List<TransactionResponse>::map)
    }
}
