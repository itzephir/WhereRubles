package com.itzephir.whererubles.feature.transactionEditor.data.mapper

import com.itzephir.whererubles.core.network.common.Id
import com.itzephir.whererubles.core.network.transaction.TransactionRequest
import com.itzephir.whererubles.feature.transactionEditor.domain.model.Transaction

fun Transaction.map(): TransactionRequest = TransactionRequest(
    accountId = Id(accountId.value),
    categoryId = Id(categoryId.value),
    amount = amount,
    transactionDate = transactionDate,
    comment = comment,
)