package com.itzephir.whererubles.core.storage.account.model

import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Currency

data class AccountRequest(
    val name: String,
    val balance: Amount,
    val currency: Currency,
)