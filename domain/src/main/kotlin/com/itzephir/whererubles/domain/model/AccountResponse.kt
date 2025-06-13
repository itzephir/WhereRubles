package com.itzephir.whererubles.domain.model

import kotlinx.datetime.Instant

data class AccountResponse(
    val id: AccountId,
    val name: String,
    val balance: String,
    val currency: String,
    val incomeStats: List<StatItem>,
    val expenseStats: List<StatItem>,
    val createdAt: Instant,
    val updatedAt: Instant,
)