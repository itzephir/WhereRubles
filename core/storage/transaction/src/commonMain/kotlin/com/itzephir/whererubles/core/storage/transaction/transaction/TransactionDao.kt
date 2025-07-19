package com.itzephir.whererubles.core.storage.transaction.transaction

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.itzephir.whererubles.core.model.Id
import kotlinx.coroutines.flow.Flow
import kotlin.time.Instant

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transactions")
    fun getAllTransactionsFlow(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactions(): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransactionById(id: Id): TransactionEntity?

    @Upsert
    fun upsert(transaction: TransactionEntity)

    @Upsert
    fun upsertAll(transactions: List<TransactionEntity>)

    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun deleteById(id: Id)

    @Query("SELECT * FROM transactions WHERE transactionDate BETWEEN :start AND :end AND accountId = :accountId")
    fun getTransactionsByPeriodFlow(accountId: Id, start: Instant, end: Instant): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE transactionDate BETWEEN :start AND :end AND accountId = :accountId")
    suspend fun getTransactionsByPeriod(accountId: Id, start: Instant, end: Instant): List<TransactionEntity>

    @Transaction
    suspend fun findOneAndReplace(id: Id, newTransaction: TransactionEntity): TransactionEntity? {
        val oldTransaction = getTransactionById(id) ?: return null
        return oldTransaction.copy(
            accountId = newTransaction.accountId,
            categoryId = newTransaction.categoryId,
            amount = newTransaction.amount,
            transactionDate = newTransaction.transactionDate,
            comment = newTransaction.comment,
        ).also { upsert(it) }
    }
}