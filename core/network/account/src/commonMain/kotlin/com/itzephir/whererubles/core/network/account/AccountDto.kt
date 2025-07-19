@file:UseSerializers()

package com.itzephir.whererubles.core.network.account

import com.itzephir.whererubles.core.network.common.Id
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlin.time.Instant

@Serializable
data class AccountDto(
    val id: Id,
    val userId: Id,
    val name: String,
    val balance: String,
    val currency: String,
    @Contextual val createdAt: Instant,
    @Contextual val updatedAt: Instant,
)