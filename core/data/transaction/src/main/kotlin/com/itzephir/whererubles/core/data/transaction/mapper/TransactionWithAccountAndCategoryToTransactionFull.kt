package com.itzephir.whererubles.core.data.transaction.mapper

import com.itzephir.whererubles.core.data.transaction.model.TransactionFull
import com.itzephir.whererubles.core.storage.account.entity.AccountEntity
import com.itzephir.whererubles.core.storage.category.CategoryEntity
import com.itzephir.whererubles.core.storage.transaction.TransactionWithAccountAndCategory
import tech.mappie.api.ObjectMappie

object TransactionWithAccountAndCategoryToTransactionFull :
    ObjectMappie<TransactionWithAccountAndCategory, TransactionFull>() {
    override fun map(from: TransactionWithAccountAndCategory): TransactionFull = mapping {
        to::id fromProperty from.transaction::id
        to::account fromProperty from::account via AccountMapper
        to::category fromProperty from::category via CategoryMapper
        to::amount fromProperty from.transaction::amount
        to::transactionDate fromProperty from.transaction::transactionDate
        to::comment fromProperty from.transaction::comment
        to::createdAt fromProperty from.transaction::createdAt
        to::updatedAt fromProperty from.transaction::updatedAt
    }

    private object AccountMapper : ObjectMappie<AccountEntity, TransactionFull.AccountBrief>()
    private object CategoryMapper : ObjectMappie<CategoryEntity, TransactionFull.Category>()
}