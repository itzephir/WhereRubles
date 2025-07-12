package com.itzephir.whererubles.feature.transactionEditor.presentation.mapper

import com.itzephir.whererubles.core.format.formatAmount
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.TransactionId

fun TransactionId.map() =
    com.itzephir.whererubles.feature.transactionEditor.domain.model.TransactionId(value)

fun Transaction.map():
        com.itzephir.whererubles.feature.transactionEditor.domain.model.Transaction {
    assert(accountId != null)
    assert(categoryId != null)
    assert(transactionDate != null)
    return com.itzephir.whererubles.feature.transactionEditor.domain.model.Transaction(
        accountId =
            com.itzephir.whererubles.feature.transactionEditor.domain.model.Transaction.AccountId(
                accountId!!.value
            ),
        categoryId = com.itzephir.whererubles.feature.transactionEditor.domain.model.Transaction.CategoryId(
            categoryId!!.value
        ),
        amount = amount.formatAmount(),
        transactionDate = transactionDate!!,
        comment = comment,
    )
}