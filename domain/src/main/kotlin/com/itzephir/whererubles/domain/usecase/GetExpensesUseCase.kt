package com.itzephir.whererubles.domain.usecase

import com.itzephir.whererubles.domain.model.AccountId
import com.itzephir.whererubles.domain.model.DateTimePeriod
import com.itzephir.whererubles.domain.model.TransactionResponse
import com.itzephir.whererubles.domain.repository.TransactionRepository
import java.util.Locale

data class ExpensesList(
    val total: String,
    val expenses: List<TransactionResponse>,
)

class GetExpensesUseCase(
    private val transactionRepository: TransactionRepository,
) {
    suspend operator fun invoke(
        accountId: AccountId,
        period: DateTimePeriod? = null,
    ): ExpensesList {
        val transactions = transactionRepository.readByAccount(accountId, period)
        val expenses = transactions.filterNot { it.category.isIncome }
        val total = expenses.fold(initial = 0.0) { acc, it -> acc + it.amount.toDouble() }.also { println(it) }
            .let { String.format(Locale.US, "%.2f", it) }
        return ExpensesList(total, expenses)
    }
}