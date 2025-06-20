package com.itzephir.whererubles.core.network.account

import kotlinx.serialization.Serializable

@Serializable
data class CreateAccountRequest(
    val name: String,
    val balance: String,
    val currency: String,
)