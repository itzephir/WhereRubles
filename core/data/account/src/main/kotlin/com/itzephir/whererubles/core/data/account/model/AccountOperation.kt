package com.itzephir.whererubles.core.data.account.model

import com.itzephir.whererubles.core.model.Currency

data class AccountOperation(
    val name: String,
    val balance: Double,
    val currency: Currency,
)