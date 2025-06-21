package com.itzephir.whererubles.feature.expenses.data.repository

import com.itzephir.whererubles.core.network.account.Account
import com.itzephir.whererubles.core.network.account.readAccounts
import com.itzephir.whererubles.feature.expenses.data.mapper.toAccountId
import com.itzephir.whererubles.feature.expenses.domain.model.AccountId
import com.itzephir.whererubles.feature.expenses.domain.repository.AccountRepository
import io.ktor.client.HttpClient
import kotlin.collections.firstOrNull

// TODO: replace with datastore implementation to not handle first account
class RemoteAccountRepository(private val httpClient: HttpClient) : AccountRepository {
    override suspend fun current(): AccountId? = httpClient.readAccounts().fold(
        ifLeft = { emptyList<Account>() },
        ifRight = { it }
    ).firstOrNull()?.id?.toAccountId()
}

