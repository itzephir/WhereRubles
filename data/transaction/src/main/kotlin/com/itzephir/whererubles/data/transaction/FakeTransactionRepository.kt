package com.itzephir.whererubles.data.transaction

import com.itzephir.whererubles.domain.model.AccountBrief
import com.itzephir.whererubles.domain.model.AccountId
import com.itzephir.whererubles.domain.model.Category
import com.itzephir.whererubles.domain.model.CategoryId
import com.itzephir.whererubles.domain.model.DateTimePeriod
import com.itzephir.whererubles.domain.model.TransactionId
import com.itzephir.whererubles.domain.model.TransactionResponse
import com.itzephir.whererubles.domain.repository.TransactionRepository
import kotlinx.datetime.Instant

class FakeTransactionRepository : TransactionRepository {
    override suspend fun readByAccount(
        accountId: AccountId,
        period: DateTimePeriod?,
    ): List<TransactionResponse> = listOf(
        TransactionResponse(
            id = TransactionId(0),
            account = AccountBrief(
                id = AccountId(0),
                name = "Account",
                balance = "100000.00",
                currency = "RUB",
            ),
            category = Category(
                id = CategoryId(0),
                name = "Аренда Квартиры",
                emoji = "\uD83C\uDFE0",
                isIncome = false,
            ),
            amount = "100000.00",
            transactionDate = Instant.parse("2025-06-13T10:22:43.162Z"),
            comment = null,
            createdAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
            updatedAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
        ),
        TransactionResponse(
            id = TransactionId(1),
            account = AccountBrief(
                id = AccountId(0),
                name = "Account",
                balance = "100000.00",
                currency = "RUB",
            ),
            category = Category(
                id = CategoryId(1),
                name = "Одежда",
                emoji = "\uD83D\uDC57",
                isIncome = false,
            ),
            amount = "100000.00",
            transactionDate = Instant.parse("2025-06-13T10:22:43.162Z"),
            comment = null,
            createdAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
            updatedAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
        ),
        TransactionResponse(
            id = TransactionId(2),
            account = AccountBrief(
                id = AccountId(0),
                name = "Account",
                balance = "100000.00",
                currency = "RUB",
            ),
            category = Category(
                id = CategoryId(2),
                name = "На собачку",
                emoji = "\uD83D\uDC36",
                isIncome = false,
            ),
            amount = "100000.00",
            transactionDate = Instant.parse("2025-06-13T10:22:43.162Z"),
            comment = "Джек",
            createdAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
            updatedAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
        ),
        TransactionResponse(
            id = TransactionId(3),
            account = AccountBrief(
                id = AccountId(0),
                name = "Account",
                balance = "100000.00",
                currency = "RUB",
            ),
            category = Category(
                id = CategoryId(2),
                name = "На собачку",
                emoji = "\uD83D\uDC36",
                isIncome = false,
            ),
            amount = "100000.00",
            transactionDate = Instant.parse("2025-06-13T10:22:43.162Z"),
            comment = "Энни",
            createdAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
            updatedAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
        ),
        TransactionResponse(
            id = TransactionId(4),
            account = AccountBrief(
                id = AccountId(0),
                name = "Account",
                balance = "100000.00",
                currency = "RUB",
            ),
            category = Category(
                id = CategoryId(3),
                name = "Ремонт квартиры",
                emoji = "РК",
                isIncome = false,
            ),
            amount = "100000.00",
            transactionDate = Instant.parse("2025-06-13T10:22:43.162Z"),
            comment = null,
            createdAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
            updatedAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
        ),
        TransactionResponse(
            id = TransactionId(6),
            account = AccountBrief(
                id = AccountId(0),
                name = "Account",
                balance = "100000.00",
                currency = "RUB",
            ),
            category = Category(
                id = CategoryId(4),
                name = "Продукты",
                emoji = "\uD83C\uDF6D",
                isIncome = false,
            ),
            amount = "100000.00",
            transactionDate = Instant.parse("2025-06-13T10:22:43.162Z"),
            comment = null,
            createdAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
            updatedAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
        ),
        TransactionResponse(
            id = TransactionId(7),
            account = AccountBrief(
                id = AccountId(0),
                name = "Account",
                balance = "100000.00",
                currency = "RUB",
            ),
            category = Category(
                id = CategoryId(5),
                name = "Спортзал",
                emoji = "\uD83C\uDFCB\uFE0F",
                isIncome = false,
            ),
            amount = "100000.00",
            transactionDate = Instant.parse("2025-06-13T10:22:43.162Z"),
            comment = null,
            createdAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
            updatedAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
        ),
        TransactionResponse(
            id = TransactionId(8),
            account = AccountBrief(
                id = AccountId(0),
                name = "Account",
                balance = "100000.00",
                currency = "RUB",
            ),
            category = Category(
                id = CategoryId(6),
                name = "Медицина",
                emoji = "\uD83D\uDC8A",
                isIncome = false,
            ),
            amount = "100000.00",
            transactionDate = Instant.parse("2025-06-13T10:22:43.162Z"),
            comment = null,
            createdAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
            updatedAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
        ),
        TransactionResponse(
            id = TransactionId(9),
            account = AccountBrief(
                id = AccountId(0),
                name = "Account",
                balance = "100000.00",
                currency = "RUB",
            ),
            category = Category(
                id = CategoryId(7),
                name = "Зарплата",
                emoji = "\uD83D\uDCB0",
                isIncome = true,
            ),
            amount = "500000.00",
            transactionDate = Instant.parse("2025-06-13T10:22:43.162Z"),
            comment = null,
            createdAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
            updatedAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
        ),
        TransactionResponse(
            id = TransactionId(10),
            account = AccountBrief(
                id = AccountId(0),
                name = "Account",
                balance = "100000.00",
                currency = "RUB",
            ),
            category = Category(
                id = CategoryId(8),
                name = "Подработка",
                emoji = "\uD83D\uDCBB",
                isIncome = true,
            ),
            amount = "100000.00",
            transactionDate = Instant.parse("2025-06-13T10:22:43.162Z"),
            comment = null,
            createdAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
            updatedAt = Instant.parse("2025-06-13T10:22:49.297433Z"),
        ),
    )
}