package com.itzephir.whererubles.core.storage.transaction

import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Id
import kotlin.time.Instant

data class TransactionRequest(
    val accountId: Id,
    val categoryId: Id,
    val amount: Amount,
    val transactionDate: Instant,
    val comment: String?,
)