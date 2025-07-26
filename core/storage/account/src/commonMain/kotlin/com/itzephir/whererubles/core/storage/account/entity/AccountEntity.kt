package com.itzephir.whererubles.core.storage.account.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.common.Currency
import kotlin.time.Instant

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Id = Id(0),
    val name: String,
    val balance: Amount,
    val currency: Currency,
    val createdAt: Instant,
    val updatedAt: Instant,
)