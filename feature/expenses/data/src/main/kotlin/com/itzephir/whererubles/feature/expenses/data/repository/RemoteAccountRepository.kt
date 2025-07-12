package com.itzephir.whererubles.feature.expenses.data.repository

import com.itzephir.whererubles.core.network.account.readAccounts
import com.itzephir.whererubles.feature.expenses.data.mapper.toAccountId
import com.itzephir.whererubles.feature.expenses.domain.model.Account
import com.itzephir.whererubles.feature.expenses.domain.repository.AccountRepository
import io.ktor.client.HttpClient
import javax.inject.Inject

/**
 * Repository for setup account
 * @param httpClient client for http calls
 */
class RemoteAccountRepository
@Inject constructor(private val httpClient: HttpClient) : AccountRepository {
    override suspend fun current(): Account? = httpClient.readAccounts().fold(
        ifLeft = { emptyList() },
        ifRight = { it }
    ).firstOrNull()?.let {
        Account(it.id.toAccountId(), it.currency, it.name)
    }
}

