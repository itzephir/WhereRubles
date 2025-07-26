package com.itzephir.whererubles.feature.expenses.data.repository

import com.itzephir.whererubles.core.data.account.AccountInteractor
import com.itzephir.whererubles.feature.expenses.domain.model.Account
import com.itzephir.whererubles.feature.expenses.domain.model.AccountId
import com.itzephir.whererubles.feature.expenses.domain.repository.AccountRepository
import javax.inject.Inject

/**
 * Repository for setup account
 * @param httpClient client for http calls
 */
class AccountRepositoryAdapter
@Inject constructor(private val accountInteractor: AccountInteractor) :
    AccountRepository {
    override suspend fun current(): Account? =
        accountInteractor.getCurrentAccount()?.let {
            Account(
                id = AccountId(it.id.value),
                currency = it.currency,
                name = it.name
            )
        }
}

