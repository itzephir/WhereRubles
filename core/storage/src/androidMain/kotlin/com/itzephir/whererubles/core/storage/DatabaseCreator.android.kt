package com.itzephir.whererubles.core.storage

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

inline fun <reified T : RoomDatabase> Context.getDatabaseOfType(name: String? = null): T {
    val name = name ?: T::class.simpleName?.lowercase()
    requireNotNull(name)
    val dbFile = getDatabasePath("$name.db")
    return Room
        .databaseBuilder<T>(context = applicationContext, name = dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .build()
}