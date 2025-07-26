package com.itzephir.whererubles.core.storage.transaction

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.itzephir.whererubles.core.model.Id
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

@Dao
interface TransactionOperationDao {
    @Query("SELECT * FROM transaction_operations")
    suspend fun getAll(): List<TransactionOperationEntity>

    @Upsert
    suspend fun upsert(operation: TransactionOperationEntity)

    @Query("DELETE FROM transaction_operations WHERE id = :id")
    suspend fun deleteById(id: Id)
}