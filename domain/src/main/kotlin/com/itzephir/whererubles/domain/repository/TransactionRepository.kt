package com.itzephir.whererubles.domain.repository

import com.itzephir.whererubles.domain.model.AccountId
import com.itzephir.whererubles.domain.model.DateTimePeriod
import com.itzephir.whererubles.domain.model.TransactionResponse

interface TransactionRepository {
    suspend fun readByAccount(
        accountId: AccountId,
        period: DateTimePeriod? = null,
    ): List<TransactionResponse>
}
