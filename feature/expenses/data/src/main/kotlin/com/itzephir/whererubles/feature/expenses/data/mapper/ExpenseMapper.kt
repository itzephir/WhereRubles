package com.itzephir.whererubles.feature.expenses.data.mapper

import com.itzephir.whererubles.core.network.common.Id
import com.itzephir.whererubles.core.network.transaction.TransactionResponse
import com.itzephir.whererubles.feature.expenses.domain.model.Expense
import com.itzephir.whererubles.feature.expenses.domain.model.ExpenseId

internal fun TransactionResponse.toExpense(): Expense = Expense(
    id = id.toExpenseId(),
    title = category.name,
    currency = account.currency,
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
    emoji = category.emoji,
)

internal fun Id.toExpenseId() = ExpenseId(value)

internal fun List<TransactionResponse>.map(): List<Expense> =
    filterNot { it.category.isIncome }.map(TransactionResponse::toExpense)
