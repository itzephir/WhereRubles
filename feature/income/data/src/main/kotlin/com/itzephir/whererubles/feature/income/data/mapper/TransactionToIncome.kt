package com.itzephir.whererubles.feature.income.data.mapper

import com.itzephir.whererubles.core.data.transaction.model.TransactionFull
import com.itzephir.whererubles.feature.income.domain.model.Account
import com.itzephir.whererubles.feature.income.domain.model.AccountId
import com.itzephir.whererubles.feature.income.domain.model.Category
import com.itzephir.whererubles.feature.income.domain.model.Income
import com.itzephir.whererubles.feature.income.domain.model.IncomeId
import tech.mappie.api.ObjectMappie

object TransactionToIncome : ObjectMappie<TransactionFull, Income>() {
    override fun map(from: TransactionFull): Income = mapping {
        to::id fromProperty from::id transform { id -> IncomeId(value = id.value) }
        to::title fromProperty from.category::name
        to::currency fromProperty from.account::currency
        to::emoji fromProperty from.category::emoji
        to::account fromProperty from::account via AccountMapper
        to::category fromProperty from::category via CategoryMapper
    }

    private object AccountMapper : ObjectMappie<TransactionFull.AccountBrief, Account>() {
        override fun map(from: TransactionFull.AccountBrief): Account = mapping {
            to::id fromProperty from::id transform { id -> AccountId(value = id.value) }
        }
    }

    private object CategoryMapper : ObjectMappie<TransactionFull.Category, Category>() {
        override fun map(from: TransactionFull.Category): Category = mapping {
            to::id fromProperty from::id transform { id -> Category.CategoryId(value = id.value) }
        }
    }

}