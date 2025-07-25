package com.itzephir.whererubles.core.storage.account.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.account.entity.AccountOperationEntity

@Dao
interface AccountOperationDao {
    @Query("SELECT * FROM account_operations")
    suspend fun getAll(): List<AccountOperationEntity>

    @Upsert
    suspend fun upsert(operation: AccountOperationEntity)

    @Query("DELETE FROM account_operations WHERE id = :id")
    suspend fun deleteById(id: Id)
}