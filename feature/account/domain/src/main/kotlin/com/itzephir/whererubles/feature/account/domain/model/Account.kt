package com.itzephir.whererubles.feature.account.domain.model

import kotlin.time.Instant

/**
 * Account entity
 */
data class Account(
    val id: AccountId,
    val userId: UserId,
    val name: String,
    val balance: String,
    val currency: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)
