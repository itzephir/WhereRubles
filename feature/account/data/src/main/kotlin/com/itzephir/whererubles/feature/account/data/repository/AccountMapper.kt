package com.itzephir.whererubles.feature.account.data.repository

import com.itzephir.whererubles.core.network.account.Account
import com.itzephir.whererubles.core.network.common.Id
import com.itzephir.whererubles.feature.account.domain.model.AccountId
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
