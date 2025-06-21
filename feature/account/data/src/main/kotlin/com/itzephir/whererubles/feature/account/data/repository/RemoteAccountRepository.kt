package com.itzephir.whererubles.feature.account.data.repository

import com.itzephir.whererubles.core.network.account.Account
import com.itzephir.whererubles.core.network.account.AccountError
import com.itzephir.whererubles.core.network.account.readAccounts
import com.itzephir.whererubles.feature.account.domain.repository.AccountRepository
import io.ktor.client.HttpClient


class RemoteAccountRepository(private val httpClient: HttpClient) : AccountRepository {
    override suspend fun getAccounts() =
        httpClient.readAccounts()
            .mapLeft(AccountError.ReadAllError::map)
            .map(List<Account>::map)
}

