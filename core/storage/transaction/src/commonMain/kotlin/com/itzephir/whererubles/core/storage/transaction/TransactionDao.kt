package com.itzephir.whererubles.core.storage.transaction

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.itzephir.whererubles.core.model.Id
import kotlin.time.Instant

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactions(): List<TransactionEntity>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransactionById(id: Id): TransactionEntity?

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransactionWithAccountAndCategoryById(id: Id): TransactionWithAccountAndCategory?

    @Upsert
    suspend fun upsert(transaction: TransactionEntity)

    @Upsert
    suspend fun upsertAll(transactions: List<TransactionEntity>)

    @Query("DELETE FROM transactions WHERE transactionDate BETWEEN :start AND :end AND accountId = :accountId")
    suspend fun deleteInPeriod(accountId: Id, start: Instant, end: Instant)

    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun deleteById(id: Id)

    @Query(value = """SELECT * FROM transactions 
        WHERE transactionDate BETWEEN :start AND :end AND accountId = :accountId 
        ORDER BY transactionDate DESC""")
    suspend fun getTransactionsByAccountIdAndPeriod(
        accountId: Id,
        start: Instant,
        end: Instant,
    ): List<TransactionWithAccountAndCategory>

    @Transaction
    suspend fun deleteAllByAccountIdAndPeriodAndUpsertAll(
        accountId: Id,
        start: Instant,
        end: Instant,
        transactions: List<TransactionEntity>
    ){
        deleteInPeriod(accountId, start, end)
        upsertAll(transactions)
    }

    @Transaction
    suspend fun findOneAndUpsert(
        id: Id,
        transactionRequest: TransactionRequest,
    ): TransactionWithAccountAndCategory? {
        val oldTransaction = getTransactionById(id) ?: return null
        val newTransaction = oldTransaction.copy(
            accountId = transactionRequest.accountId,
            categoryId = transactionRequest.categoryId,
            amount = transactionRequest.amount,
            transactionDate = transactionRequest.transactionDate,
            comment = transactionRequest.comment,
        ).also {
            upsert(it)
        }
        return requireNotNull(getTransactionWithAccountAndCategoryById(newTransaction.id))
    }
}