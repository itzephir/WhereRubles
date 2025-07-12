package com.itzephir.whererubles.feature.account.data.repository

import com.itzephir.whererubles.core.network.account.Account
import com.itzephir.whererubles.core.network.account.AccountError
import com.itzephir.whererubles.core.network.account.AccountResponse
import com.itzephir.whererubles.core.network.account.readAccountById
import com.itzephir.whererubles.core.network.account.readAccounts
import com.itzephir.whererubles.core.network.account.updateAccountById
import com.itzephir.whererubles.core.network.common.Id
import com.itzephir.whererubles.feature.account.domain.model.AccountId
import com.itzephir.whererubles.feature.account.domain.model.AccountUpdateRequest
import com.itzephir.whererubles.feature.account.domain.repository.AccountRepository
import io.ktor.client.HttpClient
import javax.inject.Inject

/**
 * Repository to setup accounts
 * @param httpClient client for http calls
 */
class RemoteAccountRepository @Inject constructor(private val httpClient: HttpClient) : AccountRepository {
    override suspend fun getAccounts() =
        httpClient.readAccounts()
            .mapLeft(AccountError.ReadAllError::map)
            .map(List<Account>::map)

    override suspend fun getAccountById(accountId: AccountId) =
        httpClient.readAccountById(Id(accountId.value))
            .mapLeft(AccountError.ReadByIdError::toGetAccountByIdError)
            .map(AccountResponse::map)

    override suspend fun updateAccountById(
        accountId: AccountId,
        accountUpdateRequest: AccountUpdateRequest,
    ) = httpClient.updateAccountById(
        id = Id(accountId.value),
        account = com.itzephir.whererubles.core.network.account.AccountUpdateRequest(
            name = accountUpdateRequest.name,
            balance = accountUpdateRequest.balance,
            currency = accountUpdateRequest.currency.origin,
        ),
    )
        .mapLeft(AccountError.UpdateByIdError::toUpdateAccountError)
        .map(Account::toAccount)
}

