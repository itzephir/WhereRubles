package com.itzephir.whererubles.feature.income.domain.repository

import com.itzephir.whererubles.feature.income.domain.model.Account
import com.itzephir.whererubles.feature.income.domain.model.AccountId

/**
 * Account Repository contract
 */
interface AccountRepository {
    suspend fun current(): Account?
}
