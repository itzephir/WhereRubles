package com.itzephir.whererubles.core.data.account.model

import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Currency
import com.itzephir.whererubles.core.model.Id
import kotlin.time.Instant

data class Account(
    val id: Id,
    val userId: Id,
    val name: String,
    val currency: Currency,
    val balance: Amount,
    val createdAt: Instant,
    val updatedAt: Instant,
)