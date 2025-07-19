package com.itzephir.whererubles.core.data.account.model

import com.itzephir.whererubles.core.model.Currency
import com.itzephir.whererubles.core.model.Id
import kotlin.time.Instant

data class AccountHistory(
    val accountId: Id,
    val accountName: String,
    val currency: Currency,
    val currentBalance: Double,
    val history: List<Change>,
) {
    data class Change(
        val id: Id,
        val accountId: Id,
        val changeType: ChangeType,
        val previousState: AccountState,
        val newState: AccountState,
        val changeTimestamp: Instant,
        val createdAt: Instant,
    ) {
        enum class ChangeType {
            MODIFICATION, CREATION
        }

        data class AccountState(
            val id: Id,
            val name: String,
            val balance: Double,
            val currency: Currency,
        )
    }
}