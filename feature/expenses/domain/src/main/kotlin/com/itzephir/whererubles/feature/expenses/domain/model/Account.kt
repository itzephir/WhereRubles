package com.itzephir.whererubles.feature.expenses.domain.model

data class Account(
    val id: AccountId,
    val currency: String,
    val name: String
)
