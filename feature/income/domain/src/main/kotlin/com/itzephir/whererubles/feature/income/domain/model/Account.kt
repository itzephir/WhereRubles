package com.itzephir.whererubles.feature.income.domain.model

import com.itzephir.whererubles.core.model.Currency

data class Account(
    val id: AccountId,
    val currency: Currency,
    val name: String
)
