package com.itzephir.whererubles.core.network.account

import com.itzephir.whererubles.core.model.Amount
import kotlinx.serialization.Serializable

@Serializable
data class AccountUpdateRequest(
    val name: String,
    val balance: Amount,
    val currency: String,
)