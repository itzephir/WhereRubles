package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.model.Transaction
import com.itzephir.whererubles.core.data.transaction.model.TransactionOperation
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
)