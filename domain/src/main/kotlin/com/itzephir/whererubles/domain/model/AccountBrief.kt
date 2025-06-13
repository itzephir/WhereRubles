package com.itzephir.whererubles.domain.model

data class AccountBrief(
    val id: AccountId,
    val name: String,
    val balance: String,
    val currency: String,
)