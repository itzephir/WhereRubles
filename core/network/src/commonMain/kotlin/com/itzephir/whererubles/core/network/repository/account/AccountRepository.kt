package com.itzephir.whererubles.core.network.repository.account

import arrow.core.Either
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.network.account.AccountDto
import com.itzephir.whererubles.core.network.account.AccountError
import com.itzephir.whererubles.core.network.account.AccountHistoryResponse
import com.itzephir.whererubles.core.network.account.AccountResponse
import com.itzephir.whererubles.core.network.account.UpdateAccountRequest
import com.itzephir.whererubles.core.network.account.CreateAccountRequest

interface AccountRepository {
    suspend fun readAll(): Either<AccountError.ReadAllError, List<AccountDto>>
    suspend fun create(createAccountRequest: CreateAccountRequest): Either<AccountError.CreateError, AccountDto>
    suspend fun readById(id: Id): Either<AccountError.ReadByIdError, AccountResponse>
    suspend fun updateById(
        id: Id,
        updateAccountRequest: UpdateAccountRequest,
    ): Either<AccountError.UpdateByIdError, AccountDto>

    suspend fun deleteById(id: Id): Either<AccountError.DeleteByIdError, Unit>

    suspend fun readHistoryById(id: Id): Either<AccountError.ReadAccountHistoryByIdError, AccountHistoryResponse>
}