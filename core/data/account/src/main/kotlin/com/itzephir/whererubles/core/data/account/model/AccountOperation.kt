package com.itzephir.whererubles.core.data.account.model

import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Currency

data class AccountOperation(
    val name: String,
    val balance: Amount,
    val currency: Currency,
)