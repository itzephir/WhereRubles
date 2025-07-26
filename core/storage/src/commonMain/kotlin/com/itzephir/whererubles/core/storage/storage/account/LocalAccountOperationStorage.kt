package com.itzephir.whererubles.core.storage.storage.account

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.account.dao.AccountOperationDao
import com.itzephir.whererubles.core.storage.account.entity.AccountOperationEntity

class LocalAccountOperationStorage(
    private val accountOperationDao: AccountOperationDao,
) : AccountOperationStorage {
    override suspend fun readAll(): List<AccountOperationEntity> =
        accountOperationDao.getAll()

    override suspend fun create(accountOperation: AccountOperationEntity): Unit =
        accountOperationDao.upsert(accountOperation)

    override suspend fun deleteById(id: Id): Unit =
        accountOperationDao.deleteById(id)
}