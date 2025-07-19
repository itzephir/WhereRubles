package com.itzephir.whererubles.feature.expenses.data.repository

import com.itzephir.whererubles.core.network.account.readAccounts
import com.itzephir.whererubles.feature.expenses.data.mapper.toAccountId
import com.itzephir.whererubles.feature.expenses.domain.model.Account
import com.itzephir.whererubles.feature.expenses.domain.repository.AccountRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.last
import javax.inject.Inject

/**
 * Repository for setup account
 * @param httpClient client for http calls
 */
class AccountRepositoryAdapter
@Inject constructor(private val accountRepository: com.itzephir.whererubles.core.data.account.AccountRepository) :
    AccountRepository {
    override suspend fun current(): Account? =
        accountRepository.getAllAccounts().first()
            .firstOrNull()?.let {
                Account(it.id.toAccountId(), it.currency.name, it.name)
            }
}

