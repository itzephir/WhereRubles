package com.itzephir.whererubles.core.storage.transaction.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.itzephir.whererubles.core.storage.common.Id
import kotlin.time.Instant

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey
    val id: Id,
    val accountId: Id,
    val categoryId: Id,
    val amount: Double,
    val transactionDate: Instant,
    val comment: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
)