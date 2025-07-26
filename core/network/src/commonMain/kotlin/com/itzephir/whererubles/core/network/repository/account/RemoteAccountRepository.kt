package com.itzephir.whererubles.core.network.repository.account

import arrow.core.Either
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.network.account.AccountDto
import com.itzephir.whererubles.core.network.account.AccountError
import com.itzephir.whererubles.core.network.account.AccountHistoryResponse
import com.itzephir.whererubles.core.network.account.AccountResponse
import com.itzephir.whererubles.core.network.account.UpdateAccountRequest
import com.itzephir.whererubles.core.network.account.CreateAccountRequest
import com.itzephir.whererubles.core.network.account.createAccount
import com.itzephir.whererubles.core.network.account.deleteAccountById
import com.itzephir.whererubles.core.network.account.readAccountById
import com.itzephir.whererubles.core.network.account.readAccountHistoryById
import com.itzephir.whererubles.core.network.account.readAccounts
import com.itzephir.whererubles.core.network.account.updateAccountById
import io.ktor.client.HttpClient

class RemoteAccountRepository(private val httpClient: HttpClient) : AccountRepository {
    override suspend fun readAll(): Either<AccountError.ReadAllError, List<AccountDto>> =
        httpClient.readAccounts()

    override suspend fun create(createAccountRequest: CreateAccountRequest)
            : Either<AccountError.CreateError, AccountDto> =
        httpClient.createAccount(createAccountRequest)

    override suspend fun readById(id: Id): Either<AccountError.ReadByIdError, AccountResponse> =
        httpClient.readAccountById(id)

    override suspend fun updateById(
        id: Id,
        updateAccountRequest: UpdateAccountRequest,
    ): Either<AccountError.UpdateByIdError, AccountDto> =
        httpClient.updateAccountById(id, updateAccountRequest)

    override suspend fun deleteById(id: Id): Either<AccountError.DeleteByIdError, Unit> =
        httpClient.deleteAccountById(id)

    override suspend fun readHistoryById(id: Id)
            : Either<AccountError.ReadAccountHistoryByIdError, AccountHistoryResponse> =
        httpClient.readAccountHistoryById(id)
}