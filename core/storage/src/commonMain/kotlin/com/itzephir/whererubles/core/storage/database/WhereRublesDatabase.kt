package com.itzephir.whererubles.core.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itzephir.whererubles.core.storage.account.dao.AccountDao
import com.itzephir.whererubles.core.storage.account.dao.AccountOperationDao
import com.itzephir.whererubles.core.storage.account.entity.AccountEntity
import com.itzephir.whererubles.core.storage.account.entity.AccountOperationEntity
import com.itzephir.whererubles.core.storage.category.CategoryDao
import com.itzephir.whererubles.core.storage.category.CategoryEntity
import com.itzephir.whererubles.core.storage.dao.AccountDaoExtended
import com.itzephir.whererubles.core.storage.transaction.TransactionDao
import com.itzephir.whererubles.core.storage.transaction.TransactionEntity
import com.itzephir.whererubles.core.storage.transaction.TransactionOperationDao
import com.itzephir.whererubles.core.storage.transaction.TransactionOperationEntity

@Database(
    entities = [
        AccountEntity::class,
        AccountOperationEntity::class,
        CategoryEntity::class,
        TransactionEntity::class,
        TransactionOperationEntity::class,
    ],
    version = 1,
)
abstract class WhereRublesDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun accountOperationDao(): AccountOperationDao
    abstract fun accountDaoExtended(): AccountDaoExtended
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
    abstract fun transactionOperationDao(): TransactionOperationDao
}