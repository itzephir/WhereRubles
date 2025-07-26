package com.itzephir.whererubles.core.storage.storage.account

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.account.dao.AccountDao
import com.itzephir.whererubles.core.storage.account.entity.AccountEntity
import com.itzephir.whererubles.core.storage.account.model.AccountRequest
import com.itzephir.whererubles.core.storage.account.preferences.CurrentAccountPreferences
import com.itzephir.whererubles.core.storage.dao.AccountDaoExtended
import com.itzephir.whererubles.core.storage.mapper.AccountRequestToAccountEntity
import com.itzephir.whererubles.core.storage.model.AccountWithTransactions

class LocalAccountStorage(
    private val accountDao: AccountDao,
    private val accountDaoExtended: AccountDaoExtended,
    private val currentAccountPreferences: CurrentAccountPreferences,
) : AccountStorage, CurrentAccountStorage {

    override suspend fun readAll(): List<AccountEntity> = accountDao.getAllAccounts()

    override suspend fun create(accountRequest: AccountRequest) =
        AccountRequestToAccountEntity.map(from = accountRequest).also {
            accountDao.upsert(it)
        }

    override suspend fun readById(id: Id): AccountWithTransactions {
        return accountDaoExtended.getAccountWithTransactionsById(id)
    }

    override suspend fun updateById(
        id: Id,
        accountRequest: AccountRequest,
    ): AccountEntity? = accountDao.findOneAndReplace(id, accountRequest)

    override suspend fun update(accountEntity: AccountEntity) {
        accountDao.upsert(accountEntity)
    }

    override suspend fun deleteById(id: Id) {
        accountDao.deleteById(id)
    }

    override suspend fun replaceAll(accounts: List<AccountEntity>): Unit =
        accountDao.replaceAll(accounts)

    override suspend fun getCurrentAccount(): AccountEntity? {
        val id = currentAccountPreferences.getCurrentId() ?: return null
        val account = accountDao.getAccountById(id)
        return account
    }

    override suspend fun setCurrentAccount(accountEntity: AccountEntity) {
        val account = accountDao.getAccountById(accountEntity.id) ?: return
        currentAccountPreferences.setCurrentId(account.id)
    }
}