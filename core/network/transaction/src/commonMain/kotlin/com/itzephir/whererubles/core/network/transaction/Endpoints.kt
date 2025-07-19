package com.itzephir.whererubles.core.network.transaction

import com.itzephir.whererubles.core.network.common.Id

const val TRANSACTIONS = "transactions"

fun transactionsById(id: Id) = "$TRANSACTIONS/${id.value}"

fun transactionsByAccountIdAndPeriod(id: Id) = "$TRANSACTIONS/account/${id.value}/period"