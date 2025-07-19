package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.model.Account
import com.itzephir.whererubles.core.data.account.model.AccountFull
import com.itzephir.whererubles.core.data.account.model.AccountOperation
import com.itzephir.whererubles.core.data.common.format
import com.itzephir.whererubles.core.model.Currency
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.network.account.AccountDto
import com.itzephir.whererubles.core.network.account.AccountResponse
import com.itzephir.whererubles.core.network.account.AccountUpdateRequest
import com.itzephir.whererubles.core.network.account.CreateAccountRequest
import com.itzephir.whererubles.core.storage.account.account.AccountEntity
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.uuid.ExperimentalUuidApi

fun AccountEntity.toAccount(): Account = Account(
    id = id,
    userId = userId,
    name = name,
    currency = currency,
    balance = balance,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun AccountDto.toAccount(): Account =
    Account(
        id = id,
        userId = userId,
        name = name,
        currency = Currency.valueOf(currency),
        balance = balance.toDouble(),
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

fun Account.toAccountEntity(): AccountEntity = AccountEntity(
    id = id,
    userId = userId,
    name = name,
    balance = balance,
    currency = currency,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun AccountOperation.toCreateAccountRequest(): CreateAccountRequest =
    CreateAccountRequest(
        name = name,
        balance = balance.format(),
        currency = currency.name,
    )

@OptIn(ExperimentalUuidApi::class)
fun AccountOperation.toAccountEntity(id: Id? = null, userId: Id? = null): AccountEntity =
    AccountEntity(
        id = id ?: Id(Random.nextInt()),
        userId = userId ?: Id(0),
        name = name,
        balance = balance,
        currency = currency,
        createdAt = Clock.System.now(),
        updatedAt = Clock.System.now(),
    )

fun AccountResponse.toAccountFull(): AccountFull = AccountFull(
    id = id,
    name = name,
    balance = balance.toDouble(),
    currency = Currency.valueOf(currency),
    incomeStats = incomeStats.map(AccountResponse.StatItem::toStatItem),
    expenseStats = expenseStats.map(AccountResponse.StatItem::toStatItem),
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun AccountResponse.StatItem.toStatItem(): AccountFull.StatItem = AccountFull.StatItem(
    categoryId = categoryId,
    categoryName = categoryName,
    emoji = emoji,
    amount = amount.toDouble(),
)

fun AccountOperation.toAccountUpdateRequest(): AccountUpdateRequest =
    AccountUpdateRequest(
        name = name,
        balance = balance.format(),
        currency = currency.name,
    )