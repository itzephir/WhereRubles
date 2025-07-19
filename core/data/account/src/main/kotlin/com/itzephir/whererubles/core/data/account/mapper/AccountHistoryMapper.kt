package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.model.AccountHistory
import com.itzephir.whererubles.core.model.Currency
import com.itzephir.whererubles.core.network.account.AccountHistoryResponse

fun AccountHistoryResponse.toAccountHistory(): AccountHistory = AccountHistory(
    accountId = accountId,
    accountName = accountName,
    currency = Currency.valueOf(currency),
    currentBalance = currentBalance.toDouble(),
    history = history.map(AccountHistoryResponse.AccountHistory::toChange),
)

fun AccountHistoryResponse.AccountHistory.toChange(): AccountHistory.Change =
    AccountHistory.Change(
        id = id,
        accountId = accountId,
        changeType = changeType.toChangeType(),
        previousState = previousState.toAccountHistoryState(),
        newState = newState.toAccountHistoryState(),
        changeTimestamp = changeTimestamp,
        createdAt = createdAt,
    )

fun AccountHistoryResponse.AccountHistory.ChangeType.toChangeType(): AccountHistory.Change.ChangeType =
    when (this) {
        AccountHistoryResponse.AccountHistory.ChangeType.CREATION     -> AccountHistory.Change.ChangeType.CREATION
        AccountHistoryResponse.AccountHistory.ChangeType.MODIFICATION -> AccountHistory.Change.ChangeType.MODIFICATION
    }

fun AccountHistoryResponse.AccountHistory.AccountState.toAccountHistoryState(): AccountHistory.Change.AccountState =
    AccountHistory.Change.AccountState(
        id = id,
        name = name,
        balance = balance.toDouble(),
        currency = Currency.valueOf(currency)
    )