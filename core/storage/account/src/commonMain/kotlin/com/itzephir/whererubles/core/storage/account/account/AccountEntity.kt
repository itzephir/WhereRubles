package com.itzephir.whererubles.core.storage.account.account

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.itzephir.whererubles.core.storage.common.Currency
import com.itzephir.whererubles.core.storage.common.Id
import kotlin.time.Instant

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey
    val id: Id,
    val userId: Id,
    val name: String,
    val balance: Double,
    val currency: Currency,
    val createdAt: Instant,
    val updatedAt: Instant,
)