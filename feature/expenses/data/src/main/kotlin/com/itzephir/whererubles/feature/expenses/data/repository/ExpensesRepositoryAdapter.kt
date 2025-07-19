package com.itzephir.whererubles.feature.expenses.data.repository

import arrow.core.Either
import arrow.core.right
import com.itzephir.whererubles.core.data.common.format
import com.itzephir.whererubles.core.data.transaction.TransactionRepository
import com.itzephir.whererubles.core.data.transaction.model.Transaction
import com.itzephir.whererubles.core.network.common.Id
import com.itzephir.whererubles.core.network.transaction.TransactionError
import com.itzephir.whererubles.core.network.transaction.TransactionResponse
import com.itzephir.whererubles.core.network.transaction.readTransactionsByAccountIdAndPeriod
import com.itzephir.whererubles.feature.expenses.data.mapper.map
import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesByAccountAndPeriodError
import com.itzephir.whererubles.feature.expenses.domain.model.Account
import com.itzephir.whererubles.feature.expenses.domain.model.AccountId
import com.itzephir.whererubles.feature.expenses.domain.model.Category
import com.itzephir.whererubles.feature.expenses.domain.model.Expense
import com.itzephir.whererubles.feature.expenses.domain.model.ExpenseId
import com.itzephir.whererubles.feature.expenses.domain.repository.ExpensesRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.last
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * Repository for setup expenses
 * @param httpClient client for http calls
 */
class ExpensesRepositoryAdapter
@Inject constructor(private val transactionRepository: TransactionRepository) : ExpensesRepository {
    @OptIn(ExperimentalTime::class)
    override suspend fun getByAccountIdAndPeriod(
        accountId: AccountId,
        start: Instant,
        end: Instant,
    ): Either<ExpensesByAccountAndPeriodError, List<Expense>> {
        return transactionRepository.getTransactionsByPeriod(
            accountId = Id(accountId.value),
            start = start,
            end = end,
        ).last().map {
            Expense(
                id = ExpenseId(it.id.value),
                title = it.title,
                currency = it.currency.name,
                amount = it.amount.format(),
                transactionDate = it.transactionDate,
                comment = it.comment,
                emoji = it.emoji,
                account = Account(
                    id = AccountId(it.account.id.value),
                    currency = it.account.currency.name,
                    name = it.account.name,
                ),
                category = Category(
                    id = Category.CategoryId(it.category.id.value),
                    name = it.category.name
                )
            )
        }.right()
    }
}
