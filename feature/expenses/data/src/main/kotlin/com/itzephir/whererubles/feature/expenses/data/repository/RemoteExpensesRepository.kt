package com.itzephir.whererubles.feature.expenses.data.repository

import arrow.core.Either
import com.itzephir.whererubles.core.network.common.Id
import com.itzephir.whererubles.core.network.transaction.TransactionError
import com.itzephir.whererubles.core.network.transaction.TransactionResponse
import com.itzephir.whererubles.core.network.transaction.readTransactionsByAccountIdAndPeriod
import com.itzephir.whererubles.feature.expenses.data.mapper.map
import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesByAccountAndPeriodError
import com.itzephir.whererubles.feature.expenses.domain.model.AccountId
import com.itzephir.whererubles.feature.expenses.domain.model.Expense
import com.itzephir.whererubles.feature.expenses.domain.repository.ExpensesRepository
import io.ktor.client.HttpClient
import kotlinx.datetime.Instant

/**
 * Repository for setup expenses
 * @param httpClient client for http calls
 */
class RemoteExpensesRepository(private val httpClient: HttpClient) : ExpensesRepository {
    override suspend fun getByAccountIdAndPeriod(
        accountId: AccountId,
        start: Instant,
        end: Instant,
    ): Either<ExpensesByAccountAndPeriodError, List<Expense>> {
        return httpClient.readTransactionsByAccountIdAndPeriod(
            accountId = Id(accountId.value),
            start = start,
            end = end,
        )
            .mapLeft(TransactionError.ReadByAccountIdAndPeriodError::map)
            .map(List<TransactionResponse>::map)
    }
}
