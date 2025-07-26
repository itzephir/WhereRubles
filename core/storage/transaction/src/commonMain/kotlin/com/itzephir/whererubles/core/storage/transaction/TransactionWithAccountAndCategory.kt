package com.itzephir.whererubles.core.storage.transaction

import androidx.room.Embedded
import androidx.room.Relation
import com.itzephir.whererubles.core.storage.account.entity.AccountEntity
import com.itzephir.whererubles.core.storage.category.CategoryEntity

data class TransactionWithAccountAndCategory(
    @Embedded
    val transaction: TransactionEntity,
    @Relation(
        parentColumn = "accountId",
        entityColumn = "id"
    )
    val account: AccountEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: CategoryEntity,
)
