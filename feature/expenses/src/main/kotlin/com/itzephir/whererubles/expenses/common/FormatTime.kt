package com.itzephir.whererubles.expenses.common

import kotlin.time.Instant
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.char

fun Instant.formatTime() = format(
    format = DateTimeComponents.Format {
        hour()
        char(':')
        minute()
    },
)
