package com.itzephir.whererubles.feature.income.data.repository

import com.itzephir.whererubles.core.network.account.Account
import com.itzephir.whererubles.core.network.account.readAccounts
import com.itzephir.whererubles.feature.income.data.mapper.toAccountId
import com.itzephir.whererubles.feature.income.domain.model.AccountId
import com.itzephir.whererubles.feature.income.domain.repository.AccountRepository
import io.ktor.client.HttpClient

/**
 * Repository for setup account
 * @param httpClient client for http calls
 */
class RemoteAccountRepository(private val httpClient: HttpClient) : AccountRepository {
    override suspend fun current(): AccountId? = httpClient.readAccounts().fold(
        ifLeft = { emptyList<Account>() },
        ifRight = { it }
    ).firstOrNull()?.id?.toAccountId()
}

