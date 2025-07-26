package com.itzephir.whererubles.core.storage.model

import androidx.room.Embedded
import androidx.room.Relation
import com.itzephir.whererubles.core.storage.account.entity.AccountEntity
import com.itzephir.whererubles.core.storage.transaction.TransactionEntity
import com.itzephir.whererubles.core.storage.transaction.TransactionWithAccountAndCategory

data class AccountWithTransactions(
    @Embedded
    val account: AccountEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "accountId",
        entity = TransactionEntity::class
    )
    val transactions: List<TransactionWithAccountAndCategory>
)