package com.itzephir.whererubles.domain.repository

import com.itzephir.whererubles.domain.model.AccountId
import com.itzephir.whererubles.domain.model.DateTimePeriod
import com.itzephir.whererubles.domain.model.TransactionResponse

/**
 * Repository to get transactions
 */
interface TransactionRepository {
    suspend fun readByAccount(
        accountId: AccountId,
        period: DateTimePeriod? = null,
    ): List<TransactionResponse>
}
