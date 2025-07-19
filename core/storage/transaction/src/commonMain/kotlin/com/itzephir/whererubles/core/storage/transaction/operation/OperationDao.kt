package com.itzephir.whererubles.core.storage.transaction.operation

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.itzephir.whererubles.core.model.Id
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDao {
    @Query("SELECT * FROM transaction_operations")
    suspend fun getAllOperations(): List<OperationEntity>

    @Query("SELECT * FROM transaction_operations")
    fun getAllOperationsFlow(): Flow<List<OperationEntity>>

    @Upsert
    suspend fun upsert(operation: OperationEntity)

    @Delete
    suspend fun delete(operation: OperationEntity)

    @Query("DELETE FROM transaction_operations WHERE id = :id")
    suspend fun deleteById(id: Id)
}