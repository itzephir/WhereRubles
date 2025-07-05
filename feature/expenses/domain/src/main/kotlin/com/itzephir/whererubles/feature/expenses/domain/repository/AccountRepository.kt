package com.itzephir.whererubles.feature.expenses.domain.repository

import com.itzephir.whererubles.feature.expenses.domain.model.Account

/**
 * Account Repository contract
 */
interface AccountRepository {
    suspend fun current(): Account?
}
