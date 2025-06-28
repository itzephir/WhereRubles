package com.itzephir.whererubles.data.account

import com.itzephir.whererubles.domain.model.AccountId
import com.itzephir.whererubles.domain.model.AccountResponse
import com.itzephir.whererubles.domain.repository.AccountRepository
import kotlinx.datetime.Instant

/**
 * Fake account implementation for mocking data
 */
class FakeAccountRepository : AccountRepository {
    override suspend fun readById(accountId: AccountId): AccountResponse = AccountResponse(
        id = AccountId(0),
        balance = "-670000.00",
        currency = "RUB",
        name = "Мой счет",
        incomeStats = listOf(),
        expenseStats = listOf(),
        createdAt = Instant.parse("2025-06-13T18:48:51.362Z"),
        updatedAt = Instant.parse("2025-06-13T18:48:51.362Z"),
    )
}
