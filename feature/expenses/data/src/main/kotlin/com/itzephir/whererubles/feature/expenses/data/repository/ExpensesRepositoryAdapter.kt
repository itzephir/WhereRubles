package com.itzephir.whererubles.feature.expenses.data.repository

import arrow.core.Either
import arrow.core.right
import com.itzephir.whererubles.core.data.transaction.TransactionInteractor
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.feature.expenses.domain.error.ExpensesByAccountAndPeriodError
import com.itzephir.whererubles.feature.expenses.domain.model.Account
import com.itzephir.whererubles.feature.expenses.domain.model.AccountId
import com.itzephir.whererubles.feature.expenses.domain.model.Category
import com.itzephir.whererubles.feature.expenses.domain.model.Expense
import com.itzephir.whererubles.feature.expenses.domain.model.ExpenseId
import com.itzephir.whererubles.feature.expenses.domain.repository.ExpensesRepository
import kotlinx.coroutines.flow.last
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

class ExpensesRepositoryAdapter
@Inject constructor(private val transactionInteractor: TransactionInteractor) : ExpensesRepository {
    @OptIn(ExperimentalTime::class)
    override suspend fun getByAccountIdAndPeriod(
        accountId: AccountId,
        start: Instant,
        end: Instant,
    ): Either<ExpensesByAccountAndPeriodError, List<Expense>> {
        return transactionInteractor.getByAccountIdAndPeriod(
            accountId = Id(accountId.value),
            start = start,
            end = end,
        ).last().map {
            Expense(
                id = ExpenseId(it.id.value),
                title = it.category.name,
                currency = it.account.currency,
                amount = it.amount,
                transactionDate = it.transactionDate,
                comment = it.comment,
                emoji = it.category.emoji,
                account = Account(
                    id = AccountId(it.account.id.value),
                    currency = it.account.currency,
                    name = it.account.name,
                ),
                category = Category(
                    id = Category.CategoryId(it.category.id.value),
                    name = it.category.name,
                    emoji = it.category.emoji
                )
            )
        }.right()
    }
}
