package com.itzephir.whererubles.feature.income.domain.repository

import com.itzephir.whererubles.feature.income.domain.model.AccountId

interface AccountRepository {
    suspend fun current(): AccountId?
}