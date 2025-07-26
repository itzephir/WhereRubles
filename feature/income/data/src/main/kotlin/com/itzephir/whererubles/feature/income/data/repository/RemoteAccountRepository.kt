package com.itzephir.whererubles.feature.income.data.repository

import com.itzephir.whererubles.core.data.account.AccountInteractor
import com.itzephir.whererubles.feature.income.domain.model.Account
import com.itzephir.whererubles.feature.income.domain.model.AccountId
import com.itzephir.whererubles.feature.income.domain.repository.AccountRepository
import javax.inject.Inject

class RemoteAccountRepository @Inject constructor(
    private val accountInteractor: AccountInteractor,
) : AccountRepository {
    override suspend fun current(): Account? = accountInteractor.getCurrentAccount()?.let {
        Account(
            id = AccountId(value = it.id.value),
            currency = it.currency,
            name = it.name
        )
    }
}

