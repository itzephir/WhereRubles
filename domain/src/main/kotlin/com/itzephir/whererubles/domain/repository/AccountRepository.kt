package com.itzephir.whererubles.domain.repository

import com.itzephir.whererubles.domain.model.AccountResponse
import com.itzephir.whererubles.domain.model.AccountId

interface AccountRepository {

    suspend fun readById(accountId: AccountId): AccountResponse
}
