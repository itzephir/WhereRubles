package com.itzephir.whererubles.feature.income.data.mapper

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.network.transaction.TransactionResponseDto
import com.itzephir.whererubles.feature.income.domain.model.Account
import com.itzephir.whererubles.feature.income.domain.model.Category
import com.itzephir.whererubles.feature.income.domain.model.Income
import com.itzephir.whererubles.feature.income.domain.model.IncomeId

internal fun TransactionResponseDto.toExpense(): Income = Income(
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

internal fun Id.toExpenseId() = IncomeId(value)

internal fun List<TransactionResponseDto>.map(): List<Income> =
    filter { it.category.isIncome }.map(TransactionResponseDto::toExpense)
