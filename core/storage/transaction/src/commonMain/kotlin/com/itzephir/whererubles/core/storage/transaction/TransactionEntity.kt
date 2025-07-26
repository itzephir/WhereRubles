package com.itzephir.whererubles.core.storage.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Id
import kotlin.time.Instant

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Id = Id(0),
    val accountId: Id,
    val categoryId: Id,
    val amount: Amount,
    val transactionDate: Instant,
    val comment: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
)
