package com.itzephir.whererubles.core.storage.dao

import androidx.room.Dao
import androidx.room.Query
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.model.AccountWithTransactions

@Dao
interface AccountDaoExtended {
    @Query("SELECT * FROM accounts WHERE id = :id")
    suspend fun getAccountWithTransactionsById(id: Id): AccountWithTransactions
}