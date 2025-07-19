package com.itzephir.whererubles.core.storage.category

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CategoryEntity::class],
    version = 1,
)
abstract class Categories : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}