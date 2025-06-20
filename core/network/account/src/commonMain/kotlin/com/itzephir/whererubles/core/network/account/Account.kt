package com.itzephir.whererubles.core.network.account

import com.itzephir.whererubles.core.network.common.Id
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val id: Id,
    val userId: Id,
    val name: String,
    val balance: String,
    val currency: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)