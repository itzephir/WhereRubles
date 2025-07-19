package com.itzephir.whererubles.core.storage.account.operation

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.itzephir.whererubles.core.storage.common.Id
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDao {
    @Query("SELECT * FROM account_operations")
    fun getAllOperationsFlow(): Flow<List<OperationEntity>>

    @Query("SELECT * FROM account_operations")
    suspend fun getAllOperations(): List<OperationEntity>

    @Upsert
    suspend fun upsertOperation(operation: OperationEntity)

    @Query("DELETE FROM account_operations WHERE id = :id")
    suspend fun deleteOperationById(id: Id)
}