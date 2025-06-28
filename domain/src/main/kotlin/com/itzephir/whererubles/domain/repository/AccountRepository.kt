package com.itzephir.whererubles.domain.repository

import com.itzephir.whererubles.domain.model.AccountResponse
import com.itzephir.whererubles.domain.model.AccountId

/**
 * Repository to get account
 */
interface AccountRepository {

    suspend fun readById(accountId: AccountId): AccountResponse
}
