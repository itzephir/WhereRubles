package com.itzephir.whererubles.feature.expenses.domain.repository

import com.itzephir.whererubles.feature.expenses.domain.model.AccountId

interface AccountRepository {
    suspend fun current(): AccountId?
}