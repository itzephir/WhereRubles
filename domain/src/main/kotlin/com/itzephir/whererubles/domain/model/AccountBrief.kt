package com.itzephir.whererubles.domain.model

/**
 * AccountBrief Entity
 */
data class AccountBrief(
    val id: AccountId,
    val name: String,
    val balance: String,
    val currency: String,
)
