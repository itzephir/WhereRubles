package com.itzephir.whererubles.core.storage.account

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.itzephir.whererubles.core.storage.account.account.AccountDao
import com.itzephir.whererubles.core.storage.account.account.AccountEntity
import com.itzephir.whererubles.core.storage.account.operation.OperationDao
import com.itzephir.whererubles.core.storage.account.operation.OperationEntity
import com.itzephir.whererubles.core.storage.common.Converter
import com.itzephir.whererubles.core.storage.common.InstantConverter

@Database(
    entities = [
        AccountEntity::class,
        OperationEntity::class,
    ],
    version = 1,
)
@TypeConverters(
    Converter::class,
    InstantConverter::class,
    OperationEntity.Converter::class,
)
abstract class Accounts : RoomDatabase() {
    abstract fun accountDao(): AccountDao

    abstract fun operationDao(): OperationDao
}