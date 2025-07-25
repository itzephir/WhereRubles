package com.itzephir.whererubles.core.network.account

import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Currency
import com.itzephir.whererubles.core.model.Id
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class AccountDto(
    val id: Id,
    val name: String,
    val balance: Amount,
    val currency: Currency,
    @Contextual val createdAt: Instant,
    @Contextual val updatedAt: Instant,
)