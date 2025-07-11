package com.itzephir.whererubles.core.network.account

import com.itzephir.whererubles.core.network.common.Id
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class AccountHistoryResponse(
    val accountId: Id,
    val accountName: String,
    val currency: String,
    val currentBalance: String,
    val history: List<AccountHistory>,
) {
    @Serializable
    data class AccountHistory(
        val id: Id,
        val accountId: Id,
        val changeType: ChangeType,
        val previousState: AccountState,
        val newState: AccountState,
        @Contextual val changeTimestamp: Instant,
        @Contextual val createdAt: Instant,
    ) {
        @Serializable
        data class AccountState(
            val id: Id,
            val name: String,
            val balance: String,
            val currency: String,
        )

        @Serializable
        enum class ChangeType {
            CREATION, MODIFICATION
        }
    }
}