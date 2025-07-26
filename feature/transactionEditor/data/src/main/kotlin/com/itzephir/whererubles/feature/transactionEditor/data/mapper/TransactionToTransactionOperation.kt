package com.itzephir.whererubles.feature.transactionEditor.data.mapper

import com.itzephir.whererubles.core.data.transaction.model.TransactionOperation
import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.feature.transactionEditor.domain.model.Transaction
import tech.mappie.api.ObjectMappie

object TransactionToTransactionOperation : ObjectMappie<Transaction, TransactionOperation>() {
    override fun map(from: Transaction): TransactionOperation = mapping {
        to::amount fromProperty from::amount transform ::Amount
    }
}