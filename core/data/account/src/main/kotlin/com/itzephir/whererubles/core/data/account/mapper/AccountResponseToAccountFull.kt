package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.model.AccountFull
import com.itzephir.whererubles.core.network.account.AccountResponse
import tech.mappie.api.ObjectMappie

object AccountResponseToAccountFull: ObjectMappie<AccountResponse, AccountFull>() {
    override fun map(from: AccountResponse): AccountFull = mapping {
        to::incomeStats fromProperty from::incomeStats via StatItemMapper.forList
        to::expenseStats fromProperty from::expenseStats via StatItemMapper.forList
    }

    private object StatItemMapper: ObjectMappie<AccountResponse.StatItem, AccountFull.StatItem>()
}