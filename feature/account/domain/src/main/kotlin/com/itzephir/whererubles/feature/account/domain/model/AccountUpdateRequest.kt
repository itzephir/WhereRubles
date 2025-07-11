package com.itzephir.whererubles.feature.account.domain.model

data class AccountUpdateRequest(
    val name: String,
    val balance: String,
    val currency: Currency,
)
