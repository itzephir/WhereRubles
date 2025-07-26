package com.itzephir.whererubles.feature.account.domain.model

import com.itzephir.whererubles.core.model.Amount

data class AccountUpdateRequest(
    val name: String,
    val balance: Amount,
    val currency: Currency,
)
