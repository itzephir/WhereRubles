package com.itzephir.whererubles.feature.expenses.domain.repository

import com.itzephir.whererubles.feature.expenses.domain.model.AccountId

/**
 * Account Repository contract
 */
interface AccountRepository {
    suspend fun current(): AccountId?
}
