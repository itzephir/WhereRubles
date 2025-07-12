package com.itzephir.whererubles.feature.income.data.repository

import com.itzephir.whererubles.core.network.account.readAccounts
import com.itzephir.whererubles.feature.income.data.mapper.toAccountId
import com.itzephir.whererubles.feature.income.domain.model.Account
import com.itzephir.whererubles.feature.income.domain.repository.AccountRepository
import io.ktor.client.HttpClient
import javax.inject.Inject

/**
 * Repository for setup account
 * @param httpClient client for http calls
 */
class RemoteAccountRepository @Inject constructor(private val httpClient: HttpClient) : AccountRepository {
    override suspend fun current(): Account? = httpClient.readAccounts().fold(
        ifLeft = { emptyList() },
        ifRight = { it }
    ).firstOrNull()?.let {
        Account(
            id = it.id.toAccountId(),
            currency = it.currency,
            name = it.name,
        )
    }
}

