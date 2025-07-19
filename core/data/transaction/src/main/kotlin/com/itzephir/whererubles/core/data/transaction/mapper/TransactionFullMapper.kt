package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.model.TransactionFull
import com.itzephir.whererubles.core.model.Currency
import com.itzephir.whererubles.core.network.transaction.TransactionResponse

fun TransactionResponse.toTransactionFull(): TransactionFull = TransactionFull(
    id = id,
    account = account.toAccountBrief(),
    category = category.toCategory(),
    amount = amount.toDouble(),
    transactionData = transactionDate,
    comment = comment,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun TransactionResponse.Category.toCategory(): TransactionFull.Category =
    TransactionFull.Category(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome,
    )

fun TransactionResponse.AccountBrief.toAccountBrief(): TransactionFull.AccountBrief =
    TransactionFull.AccountBrief(
        id = id,
        name = name,
        balance = balance.toDouble(),
        currency = Currency.valueOf(currency),
    )