package com.itzephir.whererubles.feature.account.domain.repository

import arrow.core.Either
import com.itzephir.whererubles.feature.account.domain.error.AccountError
import com.itzephir.whererubles.feature.account.domain.model.Account

/**
 * Account repository contract
 */
interface AccountRepository {
    suspend fun getAccounts(): Either<AccountError.GetAccountError, List<Account>>
}
