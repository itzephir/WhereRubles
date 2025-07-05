package com.itzephir.whererubles.feature.account.data.repository

import com.itzephir.whererubles.core.network.account.Account
import com.itzephir.whererubles.core.network.account.AccountError
import com.itzephir.whererubles.core.network.account.AccountResponse
import com.itzephir.whererubles.core.network.common.Id
import com.itzephir.whererubles.feature.account.domain.model.AccountId
import com.itzephir.whererubles.feature.account.domain.model.Currency
import com.itzephir.whererubles.feature.account.domain.model.UserId

internal fun List<Account>.map() = map(Account::toAccount)

internal fun Account.toAccount() =
    com.itzephir.whererubles.feature.account.domain.model.Account(
        id = id.toAccountId(),
        userId = userId.toUserId(),
        name = name,
        balance = balance,
        currency = currency,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

internal fun Id.toAccountId() =
    AccountId(value)

internal fun Id.toUserId() =
    UserId(value)

fun AccountResponse.StatItem.map(): com.itzephir.whererubles.feature.account.domain.model.AccountResponse.StatItem =
    com.itzephir.whererubles.feature.account.domain.model.AccountResponse.StatItem(
        categoryId = com.itzephir.whererubles.feature.account.domain.model.AccountResponse.StatItem.CategoryId(categoryId.value),
        categoryName = categoryName,
        emoji = emoji,
        amount = amount,
    )

fun AccountError.ReadByIdError.toGetAccountByIdError(): com.itzephir.whererubles.feature.account.domain.error.AccountError.GetAccountByIdError =
    when (this) {
        is AccountError.ReadByIdError.Unauthorized -> com.itzephir.whererubles.feature.account.domain.error.AccountError.GetAccountByIdError.Unauthorized
        is AccountError.ReadByIdError.NotFound     -> com.itzephir.whererubles.feature.account.domain.error.AccountError.GetAccountByIdError.NotFound
        is AccountError.ReadByIdError.WrongId      -> com.itzephir.whererubles.feature.account.domain.error.AccountError.GetAccountByIdError.WrongFormat
        is AccountError.ReadByIdError.Else         -> com.itzephir.whererubles.feature.account.domain.error.AccountError.GetAccountByIdError.Else(
            this.cause
        )
    }

fun AccountResponse.map(): com.itzephir.whererubles.feature.account.domain.model.AccountResponse =
    com.itzephir.whererubles.feature.account.domain.model.AccountResponse(
        id = AccountId(id.value),
        name = name,
        balance = balance,
        currency = Currency.Companion.fromString(currency),
        incomeStats = incomeStats.map(
            AccountResponse.StatItem::map
        ),
        expenseStats = expenseStats.map(
            AccountResponse.StatItem::map
        ),
        createdAt = createdAt,
        updatedAt = updatedAt,
    )