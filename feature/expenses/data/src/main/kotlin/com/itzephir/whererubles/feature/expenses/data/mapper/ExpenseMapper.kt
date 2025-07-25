package com.itzephir.whererubles.feature.expenses.data.mapper

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.network.transaction.TransactionResponseDto
import com.itzephir.whererubles.feature.expenses.domain.model.Account
import com.itzephir.whererubles.feature.expenses.domain.model.Category
import com.itzephir.whererubles.feature.expenses.domain.model.Expense
import com.itzephir.whererubles.feature.expenses.domain.model.ExpenseId

internal fun TransactionResponseDto.toExpense(): Expense = Expense(
    id = id.toExpenseId(),
    title = category.name,
    currency = account.currency,
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
    emoji = category.emoji,
    account = Account(account.id.toAccountId(), account.currency, account.name),
    category = Category(Category.CategoryId(category.id.value), category.name)
)

internal fun Id.toExpenseId() = ExpenseId(value)

internal fun List<TransactionResponseDto>.map(): List<Expense> =
    filterNot { it.category.isIncome }.map(TransactionResponseDto::toExpense)
