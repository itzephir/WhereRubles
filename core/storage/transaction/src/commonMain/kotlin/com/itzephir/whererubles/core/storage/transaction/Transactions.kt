package com.itzephir.whererubles.core.storage.transaction

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.itzephir.whererubles.core.storage.common.Converter
import com.itzephir.whererubles.core.storage.common.InstantConverter
import com.itzephir.whererubles.core.storage.common.OperationType
import com.itzephir.whererubles.core.storage.transaction.operation.OperationDao
import com.itzephir.whererubles.core.storage.transaction.operation.OperationEntity
import com.itzephir.whererubles.core.storage.transaction.transaction.TransactionDao
import com.itzephir.whererubles.core.storage.transaction.transaction.TransactionEntity

@Database(
    entities = [
        TransactionEntity::class,
        OperationEntity::class,
    ],
    version = 1,
)
@TypeConverters(
    Converter::class,
    InstantConverter::class,
    OperationEntity.Converter::class,
)
abstract class Transactions : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    abstract fun operationDao(): OperationDao
}