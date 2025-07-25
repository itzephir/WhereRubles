package com.itzephir.whererubles.core.storage.account.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.account.entity.AccountEntity
import com.itzephir.whererubles.core.storage.account.model.AccountRequest

@Dao
interface AccountDao {
    @Query("SELECT * FROM accounts")
    suspend fun getAllAccounts(): List<AccountEntity>

    @Query("SELECT * FROM accounts WHERE id = :id")
    suspend fun getAccountById(id: Id): AccountEntity?

    @Upsert
    suspend fun upsert(account: AccountEntity)

    @Upsert
    suspend fun upsertAll(accounts: List<AccountEntity>)

    @Query("DELETE FROM accounts")
    suspend fun deleteAll()

    @Query("DELETE FROM accounts WHERE id = :id")
    suspend fun deleteById(id: Id)

    @Transaction
    suspend fun findOneAndReplace(id: Id, accountRequest: AccountRequest) {
        val old = getAccountById(id) ?: return
        return old.copy(
            name = accountRequest.name,
            balance = accountRequest.balance,
            currency = accountRequest.currency,
        ).let { account ->
            upsert(account = account)
        }
    }

    @Transaction
    suspend fun replaceAll(accounts: List<AccountEntity>) {
        deleteAll()
        upsertAll(accounts)
    }
}