package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.common.format
import com.itzephir.whererubles.core.data.transaction.model.TransactionOperation
import com.itzephir.whererubles.core.network.transaction.TransactionRequest
import com.itzephir.whererubles.core.storage.common.OperationType
import com.itzephir.whererubles.core.storage.transaction.operation.OperationEntity

fun TransactionOperation.toTransactionRequest(): TransactionRequest =
    TransactionRequest(
        accountId = accountId,
        categoryId = categoryId,
        amount = amount.format(),
        transactionDate = transactionDate,
        comment = comment,
    )

fun TransactionOperation.toOperationEntity(): OperationEntity = OperationEntity(
    type = OperationType.Create(
        body = OperationEntity.TransactionOperationBody(
            accountId = accountId,
            categoryId = categoryId,
            amount = amount,
            transactionDate = transactionDate,
            comment = comment,
        )
    )
)

fun OperationType.Create<OperationEntity.TransactionOperationBody>.toTransactionRequest(): TransactionRequest =
    body.toTransactionRequest()

fun OperationEntity.TransactionOperationBody.toTransactionRequest(): TransactionRequest =
    TransactionRequest(
        accountId = accountId,
        categoryId = categoryId,
        amount = amount.toString(),
        transactionDate = transactionDate,
        comment = comment,
    )

fun OperationType.Update<OperationEntity.TransactionOperationBody>.toTransactionRequest(): TransactionRequest =
    body.toTransactionRequest()