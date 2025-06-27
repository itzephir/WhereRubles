package com.itzephir.whererubles.domain.usecase

import com.itzephir.whererubles.domain.model.AccountId
import com.itzephir.whererubles.domain.model.DateTimePeriod
import com.itzephir.whererubles.domain.model.ExpensesList
import com.itzephir.whererubles.domain.repository.TransactionRepository
import java.util.Locale

class GetExpensesUseCase(
    private val transactionRepository: TransactionRepository,
) {
    suspend operator fun invoke(
        accountId: AccountId,
        period: DateTimePeriod? = null,
    ): ExpensesList {
        val transactions = transactionRepository.readByAccount(accountId, period)
        val expenses = transactions.filterNot { it.category.isIncome }
        val total = expenses.fold(initial = 0.0) { acc, expense -> acc + expense.amount.toDouble() }
            .let { String.format(Locale.US, "%.2f", it) }
        return ExpensesList(total, expenses)
    }
}
