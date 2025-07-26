package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.model.AccountHistory
import com.itzephir.whererubles.core.network.account.AccountHistoryResponse
import tech.mappie.api.ObjectMappie

object AccountHistoryResponseToAccountHistory :
    ObjectMappie<AccountHistoryResponse, AccountHistory>() {
    override fun map(from: AccountHistoryResponse): AccountHistory = mapping {
        to::history fromProperty from::history via HistoryMapper.forList
    }

    private object HistoryMapper :
        ObjectMappie<AccountHistoryResponse.AccountHistory, AccountHistory.Change>() {
        override fun map(from: AccountHistoryResponse.AccountHistory): AccountHistory.Change =
            mapping {
                to::previousState fromProperty from::previousState via StateMapper
                to::newState fromProperty from::newState via StateMapper
            }

        private object StateMapper :
            ObjectMappie<AccountHistoryResponse.AccountHistory.AccountState, AccountHistory.Change.AccountState>()
    }
}