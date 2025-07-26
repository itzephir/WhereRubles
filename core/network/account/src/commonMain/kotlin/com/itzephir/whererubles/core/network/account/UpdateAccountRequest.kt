package com.itzephir.whererubles.core.network.account

import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Currency
import kotlinx.serialization.Serializable

@Serializable
data class UpdateAccountRequest(
    val name: String,
    val balance: Amount,
    val currency: Currency,
)