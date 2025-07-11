package com.itzephir.whererubles.core.network.transaction

import kotlin.time.Instant
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.char

fun Instant.format() = format(format = DateTimeComponents.Format {
    year()
    char('-')
    monthNumber()
    char('-')
    day()
})