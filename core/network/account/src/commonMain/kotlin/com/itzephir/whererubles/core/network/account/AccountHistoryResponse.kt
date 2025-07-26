package com.itzephir.whererubles.core.network.account

import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.model.Currency
import com.itzephir.whererubles.core.model.Amount
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class AccountHistoryResponse(
    val accountId: Id,
    val accountName: String,
    val currency: Currency,
    val currentBalance: Amount,
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
            val balance: Amount,
            val currency: Currency,
        )

        @Serializable
        enum class ChangeType {
            CREATION, MODIFICATION
        }
    }
}