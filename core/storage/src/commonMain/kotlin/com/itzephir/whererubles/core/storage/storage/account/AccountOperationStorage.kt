package com.itzephir.whererubles.core.storage.storage.account

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.account.entity.AccountOperationEntity

interface AccountOperationStorage {
    suspend fun readAll(): List<AccountOperationEntity>
    suspend fun create(accountOperation: AccountOperationEntity)
    suspend fun deleteById(id: Id)
}