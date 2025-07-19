package com.itzephir.whererubles.core.storage.account.account

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.itzephir.whererubles.core.storage.account.Accounts
import com.itzephir.whererubles.core.storage.common.Id
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Query("SELECT * FROM accounts")
    fun getAllAccountsFlow(): Flow<List<AccountEntity>>

    @Query("SELECT * FROM accounts")
    suspend fun getAllAccounts(): List<AccountEntity>

    @Query("SELECT * FROM accounts WHERE id = :id")
    suspend fun getAccountById(id: Id): AccountEntity?

    @Upsert
    suspend fun upsert(account: AccountEntity)

    @Upsert
    suspend fun upsertAll(vararg accounts: AccountEntity)

    @Upsert
    suspend fun upsertAll(accounts: List<AccountEntity>)

    @Query("DELETE FROM accounts")
    suspend fun deleteAll()

    @Query("DELETE FROM accounts WHERE id = :id")
    suspend fun deleteById(id: Id)

    @Transaction
    suspend fun replaceAll(vararg accounts: AccountEntity) {
        deleteAll()
        upsertAll(*accounts)
    }

    @Transaction
    suspend fun replaceAll(accounts: List<AccountEntity>) {
        deleteAll()
        upsertAll(accounts)
    }

    @Transaction
    suspend fun findOneAndReplace(id: Id, newAccount: AccountEntity): AccountEntity? {
        val old = getAccountById(id) ?: return null
        return old.copy(
            name = newAccount.name,
            balance = newAccount.balance,
            currency = newAccount.currency,
        ).also {
            upsert(it)
        }
    }
}