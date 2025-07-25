package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.network.transaction.TransactionRequest
import com.itzephir.whererubles.core.storage.transaction.TransactionWithAccountAndCategory
import tech.mappie.api.ObjectMappie

object TransactionWithAccountAndCategoryToTransactionRequest :
    ObjectMappie<TransactionWithAccountAndCategory, TransactionRequest>() {
    override fun map(from: TransactionWithAccountAndCategory): TransactionRequest = mapping {
        to::accountId fromProperty from.account::id
        to::categoryId fromProperty from.category::id
        to::amount fromProperty from.transaction::amount
        to::transactionDate fromProperty from.transaction::transactionDate
        to::comment fromProperty from.transaction::comment
    }
}