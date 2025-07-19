package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.mapper.toCategory
import com.itzephir.whererubles.core.data.transaction.model.Transaction
import com.itzephir.whererubles.core.data.transaction.model.TransactionOperation
import com.itzephir.whererubles.core.model.Currency
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.network.transaction.TransactionResponse
import com.itzephir.whererubles.core.storage.transaction.transaction.TransactionEntity
import kotlin.random.Random
import kotlin.time.Clock

fun Transaction.toTransactionEntity(): TransactionEntity = TransactionEntity(
    id = id,
    accountId = accountId,
    categoryId = categoryId,
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
    createdAt = createdAt,
    updatedAt = updatedAt,
    title = title,
    currency = currency,
    emoji = emoji,
    account = TransactionEntity.Account(
        id = account.id,
        currency = account.currency,
        name = account.name,
    ),
    category = TransactionEntity.Category(
        id = category.id,
        name = category.name,
    )
)

fun TransactionOperation.toTransactionEntity(id: Id? = null): TransactionEntity =
    TransactionEntity(
        id = id ?: Id(Random.Default.nextInt()),
        accountId = accountId,
        categoryId = categoryId,
        amount = amount,
        transactionDate = transactionDate,
        comment = comment,
        createdAt = Clock.System.now(),
        updatedAt = Clock.System.now(),
        title = "",
        currency = Currency.USD,
        emoji = "",
        account = TransactionEntity.Account(
            id = Id(0),
            currency = Currency.USD,
            name = "",
        ),
        category = TransactionEntity.Category(
            id = Id(0),
            name = "",
        ),
    )

fun TransactionEntity.toTransaction(): Transaction = Transaction(
    id = id,
    accountId = accountId,
    categoryId = categoryId,
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
    createdAt = createdAt,
    updatedAt = updatedAt,
    title = title,
    currency = currency,
    emoji = emoji,
    account = account.toAccount(),
    category = category.toCategory(),
)

private fun TransactionEntity.Category.toCategory(): Transaction.Category = Transaction.Category(
    id = id,
    name = name
)

private fun TransactionEntity.Account.toAccount(): Transaction.Account = Transaction.Account(
    id = id,
    currency = currency,
    name = name,
)

fun TransactionResponse.toTransaction(): Transaction = Transaction(
    id = id,
    accountId = account.id,
    categoryId = category.id,
    amount = amount.toDouble(),
    transactionDate = transactionDate,
    comment = comment,
    createdAt = createdAt,
    updatedAt = updatedAt,
    title = category.name,
    currency = Currency.valueOf(account.currency),
    emoji = category.emoji,
    account = Transaction.Account(
        id = account.id,
        currency = Currency.valueOf(account.currency),
        name = account.name,
    ),
    category = Transaction.Category(
        id = category.id,
        name = category.name,
    ),
)