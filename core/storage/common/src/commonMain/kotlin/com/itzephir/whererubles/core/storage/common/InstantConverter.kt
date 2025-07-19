package com.itzephir.whererubles.core.storage.common

import androidx.room.TypeConverter
import kotlin.time.Instant

class InstantConverter {

    @TypeConverter
    fun fromLongToInstant(timeInMillis: Long) = Instant.fromEpochMilliseconds(timeInMillis)

    @TypeConverter
    fun fromInstantToLong(instant: Instant) = instant.toEpochMilliseconds()
}
