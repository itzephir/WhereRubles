package com.itzephir.whererubles.core.storage.storage.account

import com.itzephir.whererubles.core.storage.account.entity.AccountEntity
import com.itzephir.whererubles.core.storage.account.model.AccountRequest
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.model.AccountWithTransactions

interface AccountStorage {
    suspend fun readAll(): List<AccountEntity>
    suspend fun create(accountRequest: AccountRequest)
    suspend fun readById(id: Id): AccountWithTransactions
    suspend fun updateById(id: Id, accountRequest: AccountRequest)
    suspend fun deleteById(id: Id)
    suspend fun replaceAll(accounts: List<AccountEntity>)
}