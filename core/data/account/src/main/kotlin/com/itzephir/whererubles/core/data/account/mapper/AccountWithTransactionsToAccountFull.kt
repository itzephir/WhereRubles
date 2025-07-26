package com.itzephir.whererubles.core.data.account.mapper

import androidx.room.Transaction
import com.itzephir.whererubles.core.data.account.model.AccountFull
import com.itzephir.whererubles.core.storage.category.CategoryEntity
import com.itzephir.whererubles.core.storage.model.AccountWithTransactions
import com.itzephir.whererubles.core.storage.transaction.TransactionWithAccountAndCategory
import tech.mappie.api.ObjectMappie

object AccountWithTransactionsToAccountFull : ObjectMappie<AccountWithTransactions, AccountFull>() {
    override fun map(from: AccountWithTransactions): AccountFull = mapping {
        to::id fromProperty from.account::id
        to::name fromProperty from.account::name
        to::balance fromProperty from.account::balance
        to::currency fromProperty from.account::currency
        to::createdAt fromProperty from.account::createdAt
        to::updatedAt fromProperty from.account::updatedAt
        to::expenseStats fromExpression { accountWithTransactions ->
            TransactionMapper.mapList(from = accountWithTransactions.transactions.filter {
                it.category.type == CategoryEntity.Type.EXPENSE
            })
        }
        to::incomeStats fromExpression {accountWithTransactions ->
            TransactionMapper.mapList(from = accountWithTransactions.transactions.filter {
                it.category.type == CategoryEntity.Type.INCOME
            })
        }
    }

    private object TransactionMapper :
        ObjectMappie<TransactionWithAccountAndCategory, AccountFull.StatItem>() {
        override fun map(from: TransactionWithAccountAndCategory): AccountFull.StatItem = mapping {
            to::categoryId fromProperty from.category::id
            to::categoryName fromProperty from.category::name
            to::emoji fromProperty from.category::emoji
            to::amount fromProperty from.transaction::amount
        }
    }
}