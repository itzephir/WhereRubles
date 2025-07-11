package com.itzephir.whererubles.domain.usecase

import com.itzephir.whererubles.domain.model.AccountId
import com.itzephir.whererubles.domain.model.DateTimePeriod
import com.itzephir.whererubles.domain.model.IncomeList
import com.itzephir.whererubles.domain.repository.TransactionRepository
import java.util.Locale

/**
 * Use case to get all income
 */
class GetIncomeUseCase(
    private val transactionRepository: TransactionRepository,
) {
    suspend operator fun invoke(
        accountId: AccountId,
        period: DateTimePeriod? = null,
    ): IncomeList {
        val transactions = transactionRepository.readByAccount(accountId, period)
        val income = transactions.filter { it.category.isIncome }
        val total = income.fold(initial = 0.0) { acc, income -> acc + income.amount.toDouble() }
            .let { String.format(Locale.US, "%.2f", it) }
        return IncomeList(total, income)
    }
}
