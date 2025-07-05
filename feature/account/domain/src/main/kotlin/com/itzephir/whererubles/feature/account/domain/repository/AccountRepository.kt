package com.itzephir.whererubles.feature.account.domain.repository

import arrow.core.Either
import com.itzephir.whererubles.feature.account.domain.error.AccountError
import com.itzephir.whererubles.feature.account.domain.model.Account
import com.itzephir.whererubles.feature.account.domain.model.AccountId
import com.itzephir.whererubles.feature.account.domain.model.AccountResponse
import com.itzephir.whererubles.feature.account.domain.model.AccountUpdateRequest

/**
 * Account repository contract
 */
interface AccountRepository {
    suspend fun getAccounts(): Either<AccountError.GetAccountError, List<Account>>

    suspend fun getAccountById(accountId: AccountId): Either<AccountError.GetAccountByIdError, AccountResponse>

    suspend fun updateAccountById(
        accountId: AccountId,
        accountUpdateRequest: AccountUpdateRequest,
    ): Either<AccountError.UpdateAccountError, Account>
}
